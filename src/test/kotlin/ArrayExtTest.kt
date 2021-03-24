import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows

class ArrayExtTest {

    @Test
    fun `test inverse ok`() {
        mockkStatic("ArrayExtKt")
        every { arrayOf(1.0, 2.0, 5.0, 10.0, 50.0).inverse() }
            .returns(arrayOf(1.0, 0.5, 0.2, 0.1, 0.02))

        val expected = arrayOf(1.0, 0.5, 0.2, 0.1, 0.02)
        val result = arrayOf(1.0, 2.0, 5.0, 10.0, 50.0).inverse()

        assertEquals(expected.contentEquals(result), true)
        verify { arrayOf(1.0, 2.0, 5.0, 10.0, 50.0).inverse() }
    }

    @Test
    fun `test inverse exception`() {
        mockkStatic("ArrayExtKt")
        every { arrayOf(0.0, 2.0, 5.0, 10.0, 50.0).inverse() } throws DivisionByZeroException()

        assertThrows<DivisionByZeroException> { arrayOf(0.0, 2.0, 5.0, 10.0, 50.0).inverse() }
        verify { arrayOf(0.0, 2.0, 5.0, 10.0, 50.0).inverse() }
    }

}