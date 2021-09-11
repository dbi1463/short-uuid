import java.lang.System.currentTimeMillis
import java.util.*

fun main() {
    val times = 21
    val size = 100_000

    println("index, toString(), shortId()")
    repeat(times) { index ->
        val uuids = (1..size).map { UUID.randomUUID() }
        val toStringStarted = currentTimeMillis()
        uuids.forEach { uuid -> uuid.toString() }
        val shortIdStarted = currentTimeMillis()
        uuids.forEach { uuid -> uuid.shortId() }
        val finished = currentTimeMillis()
        println("${index}, ${shortIdStarted - toStringStarted}, ${finished - shortIdStarted}")
    }
}
