package lesson5.dao

data class Customer(val custID: Int, val custName: String, val custTel: Int) : DAO {
    override fun toList(): List<Any> =
        listOf(custID, custName, custTel)

    override fun toValueString(): String =
        "$custID, \"$custName\", $custTel"
}