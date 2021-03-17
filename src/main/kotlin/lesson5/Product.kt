package lesson5

data class Product(val productID: Int, val producerID: Int, val productName: String, val prodCost: Int) {
    fun toList(): List<Any> =
        listOf(productID, producerID, productName, prodCost)
}