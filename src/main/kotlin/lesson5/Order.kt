package lesson5

data class Order(val orderID: Int, val productID: Int, val customerID: Int) {
    fun toList(): List<Any> =
        listOf(orderID, productID, customerID)
}