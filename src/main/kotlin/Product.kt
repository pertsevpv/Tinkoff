data class Product(
    var id: Int = 0,
    var name: String = "",
    var factory: String = "",
    var cost: Double = 0.0
) {
    fun sale(proc: Double): Double =
        cost * (1 - proc / 100.0)
}

fun product(block: Product.() -> Unit): Product =
    Product().apply(block)