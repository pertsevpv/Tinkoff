package lesson5.dao

data class Product(val productID: Int, val producerID: Int, val productName: String, val prodCost: Int) : DAO {
    override fun toList(): List<Any> =
        listOf(productID, producerID, productName, prodCost)

    override fun toValueString(): String =
        "$productID, $producerID, \"$productName\", $prodCost"
}