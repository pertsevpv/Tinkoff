package lesson5

data class Producer(val producerID: Int, val producerName: String, val rating: Int) {
    fun toList(): List<Any> =
        listOf(producerID, producerName, rating)
}