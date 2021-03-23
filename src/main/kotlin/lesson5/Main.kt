package lesson5

import lesson5.data.CustomerData
import lesson5.data.OrderData
import lesson5.data.ProducerData
import lesson5.data.ProductData
import lesson5.dbservices.*

fun line(size: Int) {
    println()
    print("-".repeat(size))
    print("/".repeat(size / 2))
    println("-".repeat(size))
    println()
}

fun oneToManyTest() {
    val producers = Table(
        "OTM_DB",
        "Producers",
        producerArgs,
        producerArgsMap
    )
    val products = Table(
        "OTM_DB",
        "Products",
        productArgs,
        productArgsMap
    )

    val dbInit = DBInit("OTM_DB", listOf(producers, products))
    val producerQueries = DBQueries(producers)
    val productQueries = DBQueries(products)

    dbInit.createAllTables()
    line(5)

    producerQueries.insertList(ProducerData().getProducers())

    line(5)

    productQueries.insertList(ProductData().getProducts())
    line(5)

    producerQueries.getProductsByProducerID(1, products).forEach {
        println(it)
    }
    line(5)

    println(producerQueries.selectByID(2))
    line(5)

    productQueries.selectUnderCost(80).forEach {
        println(it)
    }
    line(5)

    producerQueries.join(products).forEach {
        println(it)
    }
    line(5)

    dbInit.dropAllTables()
}

fun manyToManyTest() {
    val products = Table(
        "MTM_DB",
        "Products",
        productArgs,
        productArgsMap
    )
    val orders = Table(
        "MTM_DB",
        "Orders",
        orderArgs,
        orderArgsMap
    )
    val customers = Table(
        "MTM_DB",
        "Customers",
        customerArgs,
        customerArgsMap
    )

    val dbInit = DBInit("MTM_DB", listOf(products, orders, customers))

    val prodQueries = DBQueries(products)
    val ordQueries = DBQueries(orders)
    val custQueries = DBQueries(customers)

    dbInit.createAllTables()
    line(5)

    prodQueries.insertList(ProductData().getProducts())
    line(5)

    ordQueries.insertList(OrderData().getOrders())
    line(5)

    custQueries.insertList(CustomerData().getCustomers())
    line(5)

    prodQueries.getCustomersByProductID(1, orders, customers).forEach {
        println(it)
    }
    line(5)

    prodQueries.getProductsByCustomerID(1, orders, products).forEach {
        println(it)
    }
    line(5)

    ordQueries.groupCustomersByProductsID().forEach {
        println(it)
    }
    line(5)

    prodQueries.selectAndSortProducts(80).forEach {
        println(it)
    }
    line(5)

    //dbInit.dropAllTables()
}

fun main() {
    oneToManyTest()
    println()
    line(20)
    line(20)
    line(20)
    manyToManyTest()
}