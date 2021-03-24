import io.mockk.junit5.MockKExtension
import io.mockk.spyk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

//п.2
@ExtendWith(MockKExtension::class)
class QuadFuncTest {

    @ParameterizedTest
    @CsvSource("1, 2, 4, -1", "2, 2, 4, -0.5", "3, 24, 0, -4")
    fun `find extremum 1`(a: Double, b: Double, c: Double, expected: Double) {
        val func = spyk<QuadFunc>()
        func.setParams(a, b, c)

        val result = func.findExt()

        assertEquals(expected, result)
    }

    @ParameterizedTest
    @CsvSource("1, 2, 4, -56", "2, 2, 4, -0.05", "3, 24, 0, 0")
    fun `find extremum 2`(a: Double, b: Double, c: Double, expected: Double) {
        val func = spyk<QuadFunc>()
        func.setParams(a, b, c)

        val result = func.findExt()

        assertNotEquals(expected, result)
    }

    @ParameterizedTest
    @CsvSource("1, 2, 4, 100, true", "2, 2, 4, 0.5, true", "3, 24, 0, 0, true")
    fun `check growing 1`(a: Double, b: Double, c: Double, expected: Double) {
        val func = spyk<QuadFunc>()
        func.setParams(a, b, c)

        val result = func.findExt()

        assertNotEquals(expected, result)
    }

    @ParameterizedTest
    @CsvSource("1, 2, 4, -100, false", "2, 2, 4, -5, false", "3, 24, 0, -5, false")
    fun `check growing 2`(a: Double, b: Double, c: Double, x: Double, exp: Boolean) {
        val func = spyk<QuadFunc>()
        func.setParams(a, b, c)

        val result = func.isGrowing(x)

        assertEquals(exp, result)
    }
}
