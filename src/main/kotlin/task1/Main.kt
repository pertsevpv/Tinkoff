package task1

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.launch
import java.time.LocalDateTime

fun main() {
    var time = ""
    var weath = ""
    val curTime = LocalDateTime.now()
    runBlocking {
        launch {
            time = getTime(curTime)
        }
        launch {
            weath = getWeather(curTime)
        }
    }
    println(time.concat(weath))
}

fun String.concat(str: String) =
    "$this\n$str"

suspend fun getTime(now: LocalDateTime): String {
    return TimeService(now).getTime()
}

suspend fun getWeather(now: LocalDateTime): String {
    return WeatherService(now).getExtraInfo()
}