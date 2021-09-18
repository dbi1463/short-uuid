import org.junit.jupiter.api.Test
import java.util.UUID.randomUUID
import kotlin.test.assertEquals

class NamedUUIDTests {

    @Test
    fun testUUIDv5() {
        val uuid = NamedUUID.from("abc@123.xyz")
        assertEquals(5, uuid.version())
        assertEquals(randomUUID().variant(), uuid.variant())
        assertEquals("17597a5e-728f-57ed-90d9-e3b538059618", uuid.toString())
    }

    @Test
    fun testUUIDWithNamespace() {
        val uuid = NamedUUID.from("abc", "123.xyz")
        assertEquals(5, uuid.version())
        assertEquals(randomUUID().variant(), uuid.variant())
        assertEquals("e0740ce6-9246-567f-bc47-6f95d2820ac7", uuid.toString())
    }

    @Test
    fun testUUIDv4() {
        val uuid = NamedUUID.from("abc@123.xyz", "", true)
        assertEquals(4, uuid.version())
        assertEquals(randomUUID().variant(), uuid.variant())
        assertEquals("17597a5e-728f-47ed-90d9-e3b538059618", uuid.toString())
    }
}