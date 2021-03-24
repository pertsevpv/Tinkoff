interface DAO {
    fun selectByID(id: Int): Product?
    fun selectAll(): List<Product>
}