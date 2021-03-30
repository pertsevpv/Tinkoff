import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.spyk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.math.cos


@ExtendWith(MockKExtension::class)
class ProductDSLTest {

    @Test
    fun `dsl test`() {
        val prodDsl = product {
            id = 2
            name = "Bad Product"
            factory = "Bad Factory"
            cost = 1000.0
        }
        prodDsl.sale(10.0)
        val prod = mockk<Product> {
            every { id } returns 1
            every { name } returns "Cool Product"
            every { factory } returns "Cool factory"
            every { cost } returns 1000_000.0
            every { sale(35.0) } returns 650_000.0
        }

        assertAll("Testing Cool Product",
            { assertEquals(1, prod.id) },
            { assertEquals("Cool Product", prod.name) },
            { assertEquals("Cool factory", prod.factory) },
            { assertEquals(1000_000.0, prod.cost) },
            { assertEquals(650_000.0, prod.sale(35.0)) }
        )
    }
}