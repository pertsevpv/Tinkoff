package p2

class WatcherThread(
    private val list: BooleanList,
    private val t: Thread,
    private val int: Long
) : Thread() {
    override fun run() {
        while (t.isAlive) {
            println("From ${currentThread().name} ${list.count()}")
            sleep(int)
        }
    }
}