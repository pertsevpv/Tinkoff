package task1

import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

class CurrentTime {
    private val possibleWeather = listOf(
        "Sunny",
        "Rain",
        "Snow",
        "Cloudy"
    )

    val time: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
    val weather: String = possibleWeather[time.hashCode() % 4]
    val humidity: Int = time.hashCode() % 100 + 1
}

class TimeService {
    suspend fun getTime(): String {
        val time = CurrentTime()
        delay(Random.nextLong(100, 1000))
        return time.time
    }
}

class WeatherService {
    suspend fun getExtraInfo(): String {
        val time = CurrentTime()
        delay(Random.nextLong(100, 1000))
        return "${time.weather} ${time.humidity}%"
    }
}