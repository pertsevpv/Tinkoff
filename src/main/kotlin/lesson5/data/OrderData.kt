package lesson5.data

import lesson5.dao.Order

class OrderData {
    private val orderList: List<Order> = listOf(
        Order(1, 1, 1),
        Order(2, 1, 5),
        Order(3, 1, 7),
        Order(4, 1, 4),
        Order(5, 1, 6),

        Order(6, 2, 2),
        Order(7, 2, 3),
        Order(8, 2, 4),

        Order(9, 3, 4),
        Order(10, 3, 5),

        Order(11, 4, 4),
        Order(12, 4, 5),
        Order(13, 4, 6),
        Order(14, 4, 7),

        Order(15, 5, 1),
        Order(16, 5, 2),
        Order(17, 5, 3)
    )

    fun getOrders(): List<Order> =
        orderList
}