import org.junit.jupiter.api.Test
import java.util.UUID.randomUUID
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class NamedUUIDTests {

    @Test
    fun testUUIDv5() {
        val uuid = NamedUUID.from("abc@123.xyz")
        assertEquals(5, uuid.version())
        assertEquals(randomUUID().variant(), uuid.variant())
        assertEquals("fadade9b-ff70-5be2-b4b3-e1be29e27511", uuid.toString())
    }

    @Test
    fun testUUIDWithNamespace() {
        val uuid = NamedUUID.from("abc", "@123.xyz")
        assertEquals(5, uuid.version())
        assertEquals(randomUUID().variant(), uuid.variant())
        assertEquals("cda47728-893f-5f74-b7ce-7bb1773ed34f", uuid.toString())
    }

    @Test
    fun testUUIDv4() {
        val uuid = NamedUUID.from("abc@123.xyz", "", true)
        assertEquals(4, uuid.version())
        assertEquals(randomUUID().variant(), uuid.variant())
        assertEquals("fadade9b-ff70-4be2-b4b3-e1be29e27511", uuid.toString())
    }

    @Test
    fun testUUIDUniqueness() {
        val uuid1 = NamedUUID.from("abc@123.xyz") // fadade9b-ff70-5be2-b4b3-e1be29e27511
        val uuid2 = NamedUUID.from("abc", "@123.xyz") // cda47728-893f-5f74-b7ce-7bb1773ed34f
        assertNotEquals(uuid1, uuid2)
    }
}