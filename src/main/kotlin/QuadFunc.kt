import java.lang.Exception

//п.2
class QuadFunc {
    private var a: Double = 0.0
    private var b: Double = 0.0
    private var c: Double = 0.0

    fun setParams(a: Double, b: Double, c: Double) {
        this.a = a
        this.b = b
        this.c = c
    }

    fun findExt(): Double {
        if (a == 0.0) throw Exception("Not a quadratic function")
        return -b / (2 * a)
    }

    fun isGrowing(x: Double): Boolean {
        if (a == 0.0) throw Exception("Not a quadratic function")
        return 2 * a * x + b > 0
    }
}
