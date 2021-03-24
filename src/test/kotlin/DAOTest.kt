import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class)

class DAOTest {

    private val data = listOf<Product>(
        Product(1, "iPhone11", "Apple", 90_000.0),
        Product(2, "Galaxy S21", "Samsung", 80_000.0),
        Product(3, "Redmi 100", "Xiaomi", 10_000.0),
        Product(4, "P41", "Huawei", 50_000.0)
    )

    @Test
    fun `test selecting all`() {
        val dao = mockk<DAO>()
        every { dao.selectAll() } returns data

        val res = dao.selectAll()

        assertEquals(data == res, true)
    }

    @Test
    fun `test selecting by id less 5`() {
        val dao = mockk<DAO>()
        every { dao.selectByID(4) } returns data[3]

        val res = dao.selectByID(4)

        assertEquals(data[3], res)

        verify(atLeast = 1, atMost = 1) { dao.selectByID(4) }
    }

    @Test
    fun `test selecting by id more or equal 5`() {
        val dao = mockk<DAO>()
        every { dao.selectByID(7) } returns null

        val res = dao.selectByID(7)

        assertNull(res)

        verify(atLeast = 1, atMost = 1) { dao.selectByID(7) }
    }
}