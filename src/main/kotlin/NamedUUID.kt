import java.nio.ByteBuffer
import java.security.MessageDigest
import java.util.*
import kotlin.experimental.and
import kotlin.experimental.or

class NamedUUID {

    companion object {

        @JvmStatic
        fun from(name: String, namespace: String = "", asV4: Boolean = false): UUID {
            val fullName = "${name}${namespace}"
            var hashed = fullName.sha1()
            hashed.setVersionAndVariant(asV4)
            val buffer = ByteBuffer.wrap(hashed, 0, Long.SIZE_BYTES * 2)
            return UUID(buffer.long, buffer.long)
        }
    }
}

private fun String.sha1(): ByteArray {
    val bytes = this.toByteArray()
    val digest = MessageDigest.getInstance("SHA-1")
    digest.update(bytes)
    return digest.digest()
}

private fun ByteArray.setVersionAndVariant(asV4: Boolean) {
    val version: Byte = if (asV4) 0x40 else 0x50
    val variant: Byte = 0x80.toByte()
    this[6] = this[6].and(0x0f) // clear version
    this[6] = this[6].or(version)     // set version
    this[8] = this[8].and(0x3f) // clear variant
    this[8] = this[8].or(variant)     // set to IETF variant
}
