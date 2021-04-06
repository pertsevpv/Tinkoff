package p1

class MyRunnable:Runnable {
    override fun run() {
        println(getThreadInfo())
    }
}