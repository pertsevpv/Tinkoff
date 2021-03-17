package lesson5

import org.sqlite.SQLiteException

class DBInit(private val databaseName: String) {

    fun createTable(table: Table) {
        DBService(databaseName).use { dbService ->
            try {
                val sqlQuery = makeCreateQuery(table.tableName,table.argsMap)
                prepareAndExecute(dbService, sqlQuery)
                println("Table ${table.tableName} created")
            } catch (e: SQLiteException) {
                println("Something went wrong while init table")
                println(e.message)
            }
        }
    }

    fun dropTable(tableName: String) {
        DBService(databaseName).use { dbService ->
            try {
                val sqlQuery = makeDropQuery(tableName)
                prepareAndExecute(dbService, sqlQuery)
                println("Table $tableName dropped")
            } catch (e: SQLiteException) {
                println("Something went wrong while drop table")
                println(e.message)
            }
        }
    }

    private fun makeCreateQuery(tableName: String, data: Map<String, String>): String =
        "CREATE TABLE $tableName (${makeInitColumns(data)})"


    private fun makeDropQuery(tableName: String) =
        "DROP TABLE $tableName"
}