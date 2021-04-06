package p1

class MyDaemon : Thread() {
    init {
        this.isDaemon = true
    }
    override fun run(){
        println(getThreadInfo())
    }
}