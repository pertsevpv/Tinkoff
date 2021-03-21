package lesson5.dbservices

class DBInit(
    private val databaseName: String,
    private val tables: List<Table>
) {

    fun createAllTables(){
        for (t in tables)createTable(t)
    }

    fun dropAllTables(){
        for (t in tables)dropTable(t)
    }

    private fun createTable(table: Table) {
        DBService(databaseName).use { dbService ->
           runCatching {
                val sqlQuery = makeCreateQuery(table.tableName, table.argsMap)
                prepareAndExecute(dbService, sqlQuery)
                println("Table ${table.tableName} created")
           }.onFailure {
                println("Something went wrong while init table ${table.tableName}")
                println(it.message)
            }
        }
    }

    private fun dropTable(table: Table) {
        DBService(databaseName).use { dbService ->
            runCatching {
            val sqlQuery = makeDropQuery(table.tableName)
                prepareAndExecute(dbService, sqlQuery)
                println("Table ${table.tableName} dropped")
            }.onFailure {
                println("Something went wrong while drop table ${table.tableName}")
                println(it.message)
            }
        }
    }

    private fun makeCreateQuery(tableName: String, data: Map<String, DataTypes>): String =
        "CREATE TABLE IF NOT EXISTS $tableName (${makeInitColumns(data)})"


    private fun makeDropQuery(tableName: String) =
        "DROP TABLE IF EXISTS $tableName"
}