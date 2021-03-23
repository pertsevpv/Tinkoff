package lesson5.dbservices

import java.lang.StringBuilder
import java.sql.ResultSet

class Table(val dbName: String, val tableName: String, val args: List<String>, val argsMap: Map<String, DataTypes>) {
    fun toColumnString(): String =
        StringBuilder().apply {
            for (el in args.indices) {
                append(args[el])
                if (el != args.size - 1) {
                    append(", ")
                }
            }
        }.toString()
}

enum class DataTypes {
    TEXT, INT
}

val producerArgsMap: Map<String, DataTypes> = mapOf(
    "producerID" to DataTypes.INT,
    "producerName" to DataTypes.TEXT,
    "rating" to DataTypes.INT
)

val producerArgs: List<String> = listOf(
    "producerID",
    "producerName",
    "rating"
)

val productArgsMap: Map<String, DataTypes> = mapOf(
    "productID" to DataTypes.INT,
    "producerID" to DataTypes.INT,
    "productName" to DataTypes.TEXT,
    "prodCost" to DataTypes.INT
)

val productArgs: List<String> = listOf(
    "productID",
    "producerID",
    "productName",
    "prodCost"
)

val producerAndProductArgs: List<String> = listOf(
    "producerName",
    "rating",
    "productName",
    "prodCost"
)

val producerAndProductArgsMap: Map<String, DataTypes> = mapOf(
    "producerName" to DataTypes.TEXT,
    "rating" to DataTypes.INT,
    "productName" to DataTypes.TEXT,
    "prodCost" to DataTypes.INT
)

val customerArgs: List<String> = listOf(
    "custID",
    "custName",
    "custTel"
)

val customerArgsMap: Map<String, DataTypes> = mapOf(
    "custID" to DataTypes.INT,
    "custName" to DataTypes.TEXT,
    "custTel" to DataTypes.INT
)

val orderArgs: List<String> = listOf(
    "orderID",
    "productID",
    "customerID"
)

val orderArgsMap: Map<String, DataTypes> = mapOf(
    "orderID" to DataTypes.INT,
    "productID" to DataTypes.INT,
    "customerID" to DataTypes.INT
)

fun prepareAndExecute(dbService: DBService, query: String) {
    val preparedStatement = dbService.conn.prepareStatement(query)
    preparedStatement.execute()
}

fun prepareAndExecuteQuery(dbService: DBService, query: String): ResultSet {
    val preparedStatement = dbService.conn.prepareStatement(query)
    return preparedStatement.executeQuery()
}

fun makeInitColumns(data: Map<String, DataTypes>): String {
    var count = 0
    val args = StringBuilder()
    data.forEach {
        args.append(it.key).append(" ").append(it.value)
        if (count != data.size - 1) {
            args.append(", ")
        }
        count++
    }
    return args.toString()
}

fun makeColumns(data: List<String>): String {
    var count = 0
    val args = StringBuilder()
    data.forEach {
        args.append(it)
        if (count != data.size - 1) {
            args.append(", ")
        }
        count++
    }
    return args.toString()
}