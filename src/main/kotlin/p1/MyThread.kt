package p1

class MyThread : Thread() {
    override fun run() {
        println(getThreadInfo())
    }
}