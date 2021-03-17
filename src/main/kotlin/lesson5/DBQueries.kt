package lesson5

import org.sqlite.SQLiteException
import java.io.IOException
import java.sql.ResultSet
import kotlin.text.StringBuilder

class DBQueries(private val mainTable: Table) {

    private val databaseName = mainTable.dbName
    private val tableName:String = mainTable.tableName
    private val args = mainTable.args
    private val argsMap = mainTable.argsMap

    fun insert(values: List<Any>) {
        DBService(databaseName).use { dbService ->
            try {
                val sqlQuery = makeInsertQuery(values)
                prepareAndExecute(dbService, sqlQuery)
                println("Inserted $values into $tableName")
            } catch (e: SQLiteException) {
                println("Something went wrong while insert into $tableName")
                println(e.message)
            }
        }
    }

    fun selectAll(): List<String> {
        DBService(databaseName).use { dbService ->
            return try {
                val sqlQuery = "SELECT * FROM $tableName"
                val rs = prepareAndExecuteQuery(dbService, sqlQuery)
                println("Selected all from $tableName")
                getStringsFromResponse(rs)
            } catch (e: IOException) {
                println("Something went wrong while selecting from $tableName")
                println(e.message)
                emptyList()
            }
        }
    }

    fun selectById(id: Int, table: Table = this.mainTable): String? {
        DBService(databaseName).use { dbService ->
            return try {
                val sqlQuery = "SELECT * FROM ${table.tableName} WHERE ${table.args[0]} = ?"
                val preparedStatement = dbService.conn.prepareStatement(sqlQuery)
                preparedStatement.setInt(1, id)
                val rs = preparedStatement.executeQuery()
                val list = getStringsFromResponse(rs, table.argsMap)
                return if (list.isEmpty())
                    null
                 else
                    list[0]
            } catch (e: SQLiteException) {
                println("Something went wrong while selecting from $tableName")
                println(e.message)
                null
            }
        }
    }

    //Only for Products
    fun selectUnderCost(cost: Int): List<String> {
        DBService(databaseName).use { dbService ->
            return try {
                val sqlQuery = "SELECT * FROM $tableName WHERE ${args[3]} < ?"
                val preparedStatement = dbService.conn.prepareStatement(sqlQuery)
                preparedStatement.setInt(1, cost)
                val rs = preparedStatement.executeQuery()
                println("Selected elements with cost less $cost")
                getStringsFromResponse(rs)
            } catch (e: SQLiteException) {
                println("Something went wrong while selecting from $tableName")
                println(e.message)
                emptyList()
            }
        }
    }

    //Only for Producers and Products
    fun join(products: Table): List<String> {
        DBService(databaseName).use { dbService ->
            return try {
                val id = mainTable.args[0]
                val sqlQuery =
                    "SELECT ${makeColumns(producerAndProductArgs)} FROM $tableName INNER JOIN ${products.tableName} ON $tableName.$id = ${products.tableName}.$id"
                val preparedStatement = dbService.conn.prepareStatement(sqlQuery)
                val rs = preparedStatement.executeQuery()
                println("$tableName joined ${products.tableName}")
                getStringsFromResponse(rs, producerAndProductArgsMap)
            } catch (e: SQLiteException) {
                println("Something went wrong while joining")
                println(e.message)
                emptyList()
            }
        }
    }

    //Only for Producers and Products
    fun getProductsByProducerID(id: Int, table: Table): List<String> {
        DBService(databaseName).use { dbService ->
            return try {
                val sqlQuery = "SELECT * FROM ${table.tableName} WHERE ${table.args[1]} = ?"
                val preparedStatement = dbService.conn.prepareStatement(sqlQuery)
                preparedStatement.setInt(1, id)
                val rs = preparedStatement.executeQuery()
                println("Selected products by producerID = $id")
                getStringsFromResponse(rs, table.argsMap)
            } catch (e: SQLiteException) {
                println("Something went wrong while selecting")
                println(e.message)
                emptyList()
            }
        }
    }

    fun getCustomersByProductID(id: Int, orders: Table, customers: Table): List<String> =
    mtmByID(id, orders, customers, 1, 2)

    fun getProductsByCustomerID(id: Int, orders: Table, products: Table): List<String> =
        mtmByID(id, orders, products, 2, 1)

    //Only for Orders
    fun groupCustomersByProductsID(): List<String> {
        DBService(databaseName).use { dbService ->
            return try {
                val concat = "GROUP_CONCAT(${args[2]},\", \")"
                val sqlQuery = "SELECT ${args[1]}, $concat FROM $tableName GROUP BY ${args[1]}"
                val preparedStatement = dbService.conn.prepareStatement(sqlQuery)
                val rs = preparedStatement.executeQuery()
                val resMap: Map<String, String> = mapOf(
                    args[1] to "INT",
                    concat to "TEXT"
                )
                println("Grouped CustomerID by ProductID")
                getStringsFromResponse(rs, resMap)
            } catch (e: SQLiteException) {
                println("Something went wrong while selecting")
                println(e.message)
                emptyList()
            }
        }
    }


    fun selectAndSortProducts(minCost: Int): List<String> {
        DBService(databaseName).use { dbService ->
            return try {
                val sqlQuery = "SELECT * FROM $tableName WHERE ${args[3]} >= $minCost ORDER BY ${args[2]}"
                val preparedStatement = dbService.conn.prepareStatement(sqlQuery)
                val rs = preparedStatement.executeQuery()
                println("Selected all elements with min cost $minCost and sorted by their productNames")
                getStringsFromResponse(rs)
            } catch (e: SQLiteException) {
                println("Something went wrong while selecting")
                println(e.message)
                emptyList()
            }
        }
    }

    private fun mtmByID(id: Int, midTable: Table, toTable: Table, midId: Int, toInt: Int): List<String> {
        DBService(databaseName).use { dbService ->
            return try {
                val sqlQuery = "SELECT * FROM ${midTable.tableName} WHERE ${midTable.args[midId]} = ?"
                val preparedStatement = dbService.conn.prepareStatement(sqlQuery)
                preparedStatement.setInt(1, id)
                val rs = preparedStatement.executeQuery()
                getStringsFromResponse(rs, midTable.argsMap).map {
                    selectById(it.split(" ")[toInt].toInt(), toTable)!!
                }
            } catch (e: SQLiteException) {
                println("Something went wrong while selecting")
                println(e.message)
                emptyList()
            }
        }
    }

    private fun getStringsFromResponse(rs: ResultSet, argsMap: Map<String, String> = this.argsMap): List<String> {
        val ans = mutableListOf<String>()
        while (rs.next()) {
            val sb = StringBuilder()
            for (str in argsMap) {
                when (str.value) {
                    "TEXT" -> sb.append(rs.getString(str.key))
                    "INT" -> sb.append(rs.getInt(str.key))
                }
                sb.append(" ")
            }
            ans += sb.toString()
        }
        return ans
    }

    private fun makeInsertQuery(values: List<Any>) =
        "INSERT INTO $tableName(${makeColumns(args)}) VALUES(${makeValues(values)})"
}