package lesson5.dao

data class Producer(val producerID: Int, val producerName: String, val rating: Int):DAO {
    override fun toList(): List<Any> =
        listOf(producerID, producerName, rating)

    override fun toValueString(): String =
        "$producerID, \"$producerName\", $rating"
}