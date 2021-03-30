package p2

import java.util.concurrent.atomic.AtomicReference
import kotlin.concurrent.thread

fun main() {
    val list = AtomicReference<BooleanList>(BooleanList())
    val t = thread {
        repeat(100) {
            list.get().add()
            Thread.sleep(25)
        }
    }

    WatcherThread(list.get(), t,  10).apply {
        start()
    }
    WatcherThread(list.get(), t,  50).apply {
        start()
    }
    WatcherThread(list.get(), t,  100).apply {
        start()
    }

}


