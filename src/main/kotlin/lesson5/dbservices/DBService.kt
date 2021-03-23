package lesson5.dbservices

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

class DBService(private val databaseName: String) : AutoCloseable {

    val conn: Connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/$databaseName.db")
    private val stm: Statement = conn.createStatement()

    override fun close() {
        conn.close()
        stm.close()
    }
}