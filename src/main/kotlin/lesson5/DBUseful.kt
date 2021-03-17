package lesson5

import java.lang.StringBuilder
import java.sql.ResultSet

class Table(val dbName: String, val tableName: String, val args: List<String>, val argsMap: Map<String, String>)

val producerArgsMap: Map<String, String> = mapOf(
    "producerID" to "INT",
    "producerName" to "TEXT",
    "rating" to "INT"
)

val producerArgs: List<String> = listOf(
    "producerID",
    "producerName",
    "rating"
)

val productArgsMap: Map<String, String> = mapOf(
    "productID" to "INT",
    "producerID" to "INT",
    "productName" to "TEXT",
    "prodCost" to "INT"
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

val producerAndProductArgsMap: Map<String, String> = mapOf(
    "producerName" to "TEXT",
    "rating" to "INT",
    "productName" to "TEXT",
    "prodCost" to "INT"
)

val customerArgs: List<String> = listOf(
    "custID",
    "custName",
    "custTel"
)

val customerArgsMap: Map<String, String> = mapOf(
    "custID" to "INT",
    "custName" to "TEXT",
    "custTel" to "INT"
)

val orderArgs: List<String> = listOf(
    "orderID",
    "productID",
    "customerID"
)

val orderArgsMap: Map<String, String> = mapOf(
    "orderID" to "INT",
    "productID" to "INT",
    "customerID" to "INT"
)

fun prepareAndExecute(dbService: DBService, query: String) {
    val preparedStatement = dbService.conn.prepareStatement(query)
    preparedStatement.execute()
}

fun prepareAndExecuteQuery(dbService: DBService, query: String): ResultSet {
    val preparedStatement = dbService.conn.prepareStatement(query)
    return preparedStatement.executeQuery()
}

fun makeInitColumns(data: Map<String, String>): String {
    var count = 0;
    val args: StringBuilder = StringBuilder()
    data.forEach {
        args.append(it.key).append(" ").append(it.value)
        if (count != data.size - 1) args.append(", ")
        count++
    }
    return args.toString()
}

fun makeColumns(data: List<String>): String {
    var count = 0;
    val args: StringBuilder = StringBuilder()
    data.forEach {
        args.append(it)
        if (count != data.size - 1) args.append(", ")
        count++
    }
    return args.toString()
}

fun makeValues(data: List<Any>): String {
    var count = 0;
    val args = StringBuilder()
    data.forEach {
        if (it is String)
            args.append("\"$it\"")
        else if (it is Int)
            args.append(it)
        if (count != data.size - 1) args.append(", ")
        count++
    }
    return args.toString()
}