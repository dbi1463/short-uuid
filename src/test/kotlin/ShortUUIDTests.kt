import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID.fromString
import java.util.UUID.randomUUID
import kotlin.test.assertEquals

class ShortUUIDTests {

    @Test
    fun testIllegalCharacters() {
        val uuid = randomUUID()
        assertThrows<IllegalArgumentException> {
            uuid.shortId("0123456789")
        }
    }

    @Test
    fun testInsufficientCharacters() {
        val uuid = randomUUID()
        assertThrows<IllegalArgumentException> {
            uuid.shortId("0123456789abcde")
        }
    }

    @Test
    fun testNotShortId() {
        assertThrows<IllegalArgumentException> {
            ShortUUID.from("123456")
        }
    }

    @Test
    fun testShortIdWithDefaultCharacters() {
        val uuid = fromString("53e6693a-0aaa-4e94-a116-2a397fc89975")
        val shortId = uuid.shortId()
        assertEquals("1jVCAW2GFeBa4mazB-O9BR", shortId)
        assertEquals(uuid, ShortUUID.from(shortId))
    }

    @Test
    fun testShortIdWith32Characters() {
        val characters = "0123456789ABCDEFGHJKLMNPRSTUVWXY"
        val uuid = fromString("fbcf8ca0-a0b7-4e8c-8a05-f0be7c72f022")
        val shortId = uuid.shortId(characters)
        assertEquals("7USX6A185P9T68L1FGPSX75V12", shortId)
        assertEquals(uuid, ShortUUID.from(shortId, characters))
    }
}
