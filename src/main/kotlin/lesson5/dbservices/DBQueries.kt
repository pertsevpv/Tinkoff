package lesson5.dbservices

import lesson5.dao.DAO
import org.sqlite.SQLiteException
import java.io.IOException
import java.lang.Exception
import java.sql.ResultSet
import kotlin.text.StringBuilder

class DBQueries(private val mainTable: Table) {

    private val databaseName = mainTable.dbName
    private val tableName: String = mainTable.tableName
    private val args = mainTable.args
    private val argsMap = mainTable.argsMap

    fun insert(value: DAO) =
        insertList(listOf(value))

    fun insertList(values: List<DAO>) {
        DBService(databaseName).use { dbService ->
            kotlin.runCatching {
                val sqlQuery: StringBuilder =
                    StringBuilder("INSERT INTO $tableName(${mainTable.toColumnString()}) VALUES ")
                for (vl in values.indices) {
                    sqlQuery.append("(").append(values[vl].toValueString()).append(")")
                    if (vl != values.size - 1)
                        sqlQuery.append(", ")
                    else
                        sqlQuery.append(";")
                }
                prepareAndExecute(dbService, sqlQuery.toString())
                println("Inserted $values into $tableName")
            }.onFailure {
                println("Something went wrong while insert into $tableName")
                println(it.message)
            }
        }
    }

    fun selectAll(): List<String> {
        return DBService(databaseName).use { dbService ->
            runCatching {
                val sqlQuery = "SELECT * FROM $tableName"
                val rs = prepareAndExecuteQuery(dbService, sqlQuery)
                println("Selected all from $tableName")
                val ans = getStringsFromResponse(rs)
                rs.close()
                ans
            }.onFailure {
                println("Something went wrong while selecting from $tableName")
                println(it.message)
            }.getOrElse { emptyList() }
        }
    }

    private fun selectByIdFrom(id: Int, table: Table): String? {
        return DBService(databaseName).use { dbService ->
            runCatching {
                val sqlQuery = "SELECT * FROM ${table.tableName} WHERE ${table.args[0]} = ?"
                val preparedStatement = dbService.conn.prepareStatement(sqlQuery)
                preparedStatement.setInt(1, id)
                val rs = preparedStatement.executeQuery()
                val list = getStringsFromResponse(rs, table.argsMap)
                rs.close()
                when {
                    list.isEmpty() -> {
                        println("No elements with id $id")
                        null
                    }
                    list.size != 1 -> {
                        println("Many elements with id $id")
                        null
                    }
                    else -> list[0]
                }
            }.onFailure {
                println("Something went wrong while selecting from $tableName")
                println(it.message)
            }.getOrElse { null }
        }
    }

    fun selectByID(id: Int) =
        selectByIdFrom(id, this.mainTable)

    //Only for Products
    fun selectUnderCost(cost: Int): List<String> {
        return DBService(databaseName).use { dbService ->
            runCatching {
                val sqlQuery = "SELECT * FROM $tableName WHERE ${args[3]} < ?"
                val preparedStatement = dbService.conn.prepareStatement(sqlQuery)
                preparedStatement.setInt(1, cost)
                val rs = preparedStatement.executeQuery()
                println("Selected elements with cost less $cost")
                getStringsFromResponse(rs)
            }.onFailure {
                println("Something went wrong while selecting from $tableName")
                println(it.message)
            }.getOrElse { emptyList() }
        }
    }

    //Only for Producers and Products
    fun join(products: Table): List<String> {
        return DBService(databaseName).use { dbService ->
            runCatching {
                val id = mainTable.args[0]
                val sqlQuery =
                    "SELECT ${makeColumns(producerAndProductArgs)} FROM $tableName INNER JOIN ${products.tableName} ON $tableName.$id = ${products.tableName}.$id"
                val preparedStatement = dbService.conn.prepareStatement(sqlQuery)
                val rs = preparedStatement.executeQuery()
                println("$tableName joined ${products.tableName}")
                val ans = getStringsFromResponse(rs, producerAndProductArgsMap)
                rs.close()
                ans
            }.onFailure {
                println("Something went wrong while joining")
                println(it.message)
            }.getOrElse { emptyList() }
        }
    }

    //Only for Producers and Products
    fun getProductsByProducerID(id: Int, table: Table): List<String> {
        return DBService(databaseName).use { dbService ->
            runCatching {
                val sqlQuery = "SELECT * FROM ${table.tableName} WHERE ${table.args[1]} = ?"
                val preparedStatement = dbService.conn.prepareStatement(sqlQuery)
                preparedStatement.setInt(1, id)
                val rs = preparedStatement.executeQuery()
                println("Selected products by producerID = $id")
                val ans = getStringsFromResponse(rs, table.argsMap)
                rs.close()
                ans
            }.onFailure {
                println("Something went wrong while selecting")
                println(it.message)
            }.getOrElse { emptyList() }
        }
    }

    fun getCustomersByProductID(id: Int, orders: Table, customers: Table): List<String> =
        mtmByID(id, orders, customers, 1, 2)

    fun getProductsByCustomerID(id: Int, orders: Table, products: Table): List<String> =
        mtmByID(id, orders, products, 2, 1)

    //Only for Orders
    fun groupCustomersByProductsID(): List<String> {
        return DBService(databaseName).use { dbService ->
            runCatching {
                val concat = "GROUP_CONCAT(${args[2]},\", \")"
                val sqlQuery = "SELECT ${args[1]}, $concat FROM $tableName GROUP BY ${args[1]}"
                val preparedStatement = dbService.conn.prepareStatement(sqlQuery)
                val rs = preparedStatement.executeQuery()
                val resMap: Map<String, DataTypes> = mapOf(
                    args[1] to DataTypes.INT,
                    concat to DataTypes.TEXT
                )
                println("Grouped CustomerID by ProductID")
                val ans = getStringsFromResponse(rs, resMap)
                rs.close()
                ans
            }.onFailure {
                println("Something went wrong while selecting")
                println(it.message)
            }.getOrElse { emptyList() }
        }
    }


    fun selectAndSortProducts(minCost: Int): List<String> {
        return DBService(databaseName).use { dbService ->
            runCatching {
                val sqlQuery = "SELECT * FROM $tableName WHERE ${args[3]} >= $minCost ORDER BY ${args[2]}"
                val preparedStatement = dbService.conn.prepareStatement(sqlQuery)
                val rs = preparedStatement.executeQuery()
                println("Selected all elements with min cost $minCost and sorted by their productNames")
                val ans = getStringsFromResponse(rs)
                rs.close()
                ans
            }.onFailure {
                println("Something went wrong while selecting")
                println(it.message)
            }.getOrElse { emptyList() }
        }
    }

    private fun mtmByID(id: Int, midTable: Table, toTable: Table, midId: Int, toInt: Int): List<String> {
        return DBService(databaseName).use { dbService ->
            runCatching {
                val sqlQuery = "SELECT * FROM ${midTable.tableName} WHERE ${midTable.args[midId]} = ?"
                val preparedStatement = dbService.conn.prepareStatement(sqlQuery)
                preparedStatement.setInt(1, id)
                val rs = preparedStatement.executeQuery()
                val ans = getStringsFromResponse(rs, midTable.argsMap).map {
                    selectByIdFrom(it.split(" ")[toInt].toInt(), toTable) ?: ""
                }
                rs.close()
                ans
            }.onFailure {
                println("Something went wrong while selecting")
                println(it.message)
            }.getOrElse { emptyList() }
        }
    }

    private fun getStringsFromResponse(rs: ResultSet, argsMap: Map<String, DataTypes> = this.argsMap): List<String> {
        val ans = mutableListOf<String>()
        val sb = StringBuilder()
        while (rs.next()) {
            sb.clear()
            for (str in argsMap) {
                when (str.value) {
                    DataTypes.TEXT -> sb.append(rs.getString(str.key))
                    DataTypes.INT -> sb.append(rs.getInt(str.key))
                }
                sb.append(" ")
            }
            ans += sb.toString()
        }
        return ans
    }
}