import java.math.BigInteger
import java.nio.ByteBuffer
import java.util.UUID
import kotlin.math.*

private const val defaultCharacters: String = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_-"

fun UUID.shortId(characters: String = defaultCharacters): String {
    val exponent = assertCharactersSize(characters)
    val lookupTable = characters.toCharArray()
    val base = 2.toDouble().pow(exponent).toInt().toBigInteger()
    val buffer = StringBuffer()
    var number = BigInteger(this.toBytes())
    do {
        val mod = number.mod(base)
        number = number.divide(base)
        buffer.append(lookupTable[mod.toInt()])
    } while (number.signum() != 0)
    return buffer.toString().reversed()
}

class ShortUUID {

    companion object {

        @JvmStatic
        fun from(shortId: String, characters: String = defaultCharacters): UUID {
            val exponent = assertCharactersSize(characters)
            if (shortId.length != ceil((Long.SIZE_BITS * 2).toDouble() / exponent).toInt()) {
                throw IllegalArgumentException("the string could not be represented with the characters")
            }
            val base = 2.toDouble().pow(exponent).toInt().toBigInteger()
            var number = BigInteger.valueOf(0)
            shortId.forEach { char ->
                val index = characters.indexOf(char).toBigInteger()
                number = number.times(base).add(index)
            }
            val bytes = number.toByteArray()
            val length = Long.SIZE_BYTES * 2
            val offset = if (bytes.size > length) (bytes.size - length) else 0
            val buffer = ByteBuffer.wrap(bytes, offset, length)
            return UUID(buffer.long, buffer.long)
        }
    }
}

private fun Double.isInteger(): Boolean {
    return this.isFinite() && this.compareTo(ceil(this)) == 0
}

private fun UUID.toBytes(): ByteArray {
    // plus 0 to become positive value
    val buffer = ByteBuffer.allocate((Long.SIZE_BYTES * 2) + 1)
    buffer.put(0)
    buffer.putLong(this.mostSignificantBits)
    buffer.putLong(this.leastSignificantBits)
    return buffer.array()
}

private fun assertCharactersSize(characters: String): Int {
    val exponent = log2(characters.length.toDouble())
    if (!exponent.isInteger()) {
        throw IllegalArgumentException("must have 2 ^ n characters")
    }
    if (exponent <= 4) {
        throw IllegalArgumentException("less than 16 characters")
    }
    return exponent.toInt()
}
