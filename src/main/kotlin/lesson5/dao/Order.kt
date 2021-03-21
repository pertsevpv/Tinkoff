package lesson5.dao

data class Order(val orderID: Int, val productID: Int, val customerID: Int):DAO {
    override fun toList(): List<Any> =
        listOf(orderID, productID, customerID)

    override fun toValueString(): String =
        "$orderID, $productID, $customerID"
}