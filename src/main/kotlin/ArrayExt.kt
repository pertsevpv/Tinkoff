import java.lang.Exception

class DivisionByZeroException(mes: String = "") : Exception("Division by zero\n$mes")


fun Array<Double>.inverse(): Array<Double>  {
    for (i in this.indices) {
        if (this[i] == 0.0) throw DivisionByZeroException()
        else this[i] = 1 / this[i]
    }
    return this
}