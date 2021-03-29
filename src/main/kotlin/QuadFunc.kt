import java.lang.Exception

//п.2
class QuadFunc(
    private var a: Double,
    private var b: Double,
    private var c: Double
) {

    fun findExt(): Double {
        if (a == 0.0) throw Exception("Not a quadratic function")
        return -b / (2 * a)
    }

    fun isGrowing(x: Double): Boolean {
        if (a == 0.0) throw Exception("Not a quadratic function")
        return 2 * a * x + b > 0
    }
}
