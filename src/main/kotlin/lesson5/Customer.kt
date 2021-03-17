package lesson5

data class Customer(val custID: Int, val custName: String, val custTel: Int) {
    fun toList(): List<Any> =
        listOf(custID, custName, custTel)
}