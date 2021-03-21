package lesson5.data

import lesson5.dao.Customer

class CustomerData {
    private val customerList: List<Customer> = listOf(
        Customer(1, "Alex", 234896),
        Customer(2, "Nikolai", 234423),
        Customer(3, "Maxim", 856231),
        Customer(4, "Kate", 478167),
        Customer(5, "Maria", 984542),
        Customer(6, "Olga", 234742),
        Customer(7, "Ivan", 234673)
    )

    fun getCustomers() =
        customerList

}