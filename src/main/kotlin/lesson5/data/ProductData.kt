package lesson5.data

import lesson5.dao.Product

class ProductData {

    private val productList: List<Product> = listOf(
        Product(1, 1, "iPhoneX", 100),
        Product(2, 1, "iPhone8", 90),
        Product(3, 1, "iPhone7", 80),
        Product(4, 1, "iPhone6S", 75),
        Product(5, 2, "GalaxyS20", 95),
        Product(6, 2, "GalaxyA71", 85),
        Product(7, 2, "GalaxyA80", 75),
        Product(8, 3, "Redmi8", 65),
        Product(9, 3, "Redmi4A", 60),
        Product(10, 4, "P40", 90)
    )

    fun getProducts(): List<Product> =
        productList
}