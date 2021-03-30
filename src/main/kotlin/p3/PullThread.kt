package p3

import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

class PullThread(private val pull: Int) : Thread() {
    override fun run() {
        val i = AtomicInteger()
        repeat(pull) {
            thread {
                while (i.get() < 1_000_000) i.incrementAndGet()
            }
        }
    }
}