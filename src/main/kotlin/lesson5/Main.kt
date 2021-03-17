package lesson5

fun line() = println("\n-----///-----\n")

fun oneToManyTest() {
    val producers = Table("OTM_DB", "Producers", producerArgs, producerArgsMap)
    val products = Table("OTM_DB", "Products", productArgs, productArgsMap)

    val dbInit = DBInit("OTM_DB")
    val producerQueries = DBQueries(producers)
    val productQueries = DBQueries(products)

    dbInit.createTable(producers)
    dbInit.createTable(products)
    line()

    for (prod in ProducerData().getProducers()) {
        producerQueries.insert(prod.toList())
    }
    line()

    for (prod in ProductData().getProducts()) {
        productQueries.insert(prod.toList())
    }
    line()

    producerQueries.getProductsByProducerID(1, products).forEach {
        println(it)
    }
    line()

    println(producerQueries.selectById(2))
    line()

    productQueries.selectUnderCost(80).forEach {
        println(it)
    }
    line()

    producerQueries.join(products).forEach {
        println(it)
    }
    line()

    dbInit.dropTable("Producers")
    dbInit.dropTable("Products")
}

fun manyToManyTest() {
    val products = Table("MTM_DB", "Products", productArgs, productArgsMap)
    val orders = Table("MTM_DB", "Orders", orderArgs, orderArgsMap)
    val customers = Table("MTM_DB", "Customers", customerArgs, customerArgsMap)

    val dbInit = DBInit("MTM_DB")

    val prodQueries = DBQueries(products)
    val ordQueries = DBQueries(orders)
    val custQueries = DBQueries(customers)

    dbInit.createTable(products)
    dbInit.createTable(orders)
    dbInit.createTable(customers)
    line()

    for (prod in ProductData().getProducts()) {
        prodQueries.insert(prod.toList())
    }
    line()

    for (order in OrderData().getOrders()) {
        ordQueries.insert(order.toList())
    }
    line()

    for (cust in CustomerData().getCustomers()) {
        custQueries.insert(cust.toList())
    }
    line()

    prodQueries.getCustomersByProductID(1, orders, customers).forEach {
        println(it)
    }
    line()

    prodQueries.getProductsByCustomerID(1, orders, products).forEach {
        println(it)
    }
    line()

    ordQueries.groupCustomersByProductsID().forEach {
        println(it)
    }
    line()

    prodQueries.selectAndSortProducts(80).forEach {
        println(it)
    }
    line()

    dbInit.dropTable("Products")
    dbInit.dropTable("Orders")
    dbInit.dropTable("Customers")
}

fun main() {
    oneToManyTest()
    println()
    println("--------------------------/////////------------------------")
    println("-------------------------/////////-------------------------")
    println("------------------------/////////--------------------------\n")
    manyToManyTest()
}