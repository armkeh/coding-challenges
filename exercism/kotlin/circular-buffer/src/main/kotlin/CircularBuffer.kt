import kotlin.collections.ArrayDeque

/**
 * Exception thrown when attempting to read, but the buffer is empty.
 */
class EmptyBufferException: Exception("Buffer is empty")

/**
 * Exception thrown when trying to write (not overwrite), but the buffer is full.
 */
class BufferFullException: Exception("Buffer is full")

/**
 * A circular buffer of a given size.
 *
 * @param T     The type stored in the buffer.
 * @param size  The size of the buffer. Once [size] elements or more are added to the buffer,
 *              further [write]s will fail, and [overwrite]s will overwrite elements
 *              from oldest to newest.
 */
class CircularBuffer<T>(size: Int) {
    // We use a double-ended queue for the buffer, appending to the end,
    // dropping at the front when full, and reading from the front.
    private val buffer: ArrayDeque<T> = ArrayDeque<T>()

    private val maxSize: Int = size

    /**
     * Read the oldest element from the buffer, and drop it from the buffer.
     * Throws an exception if the buffer is empty.
     * @return The oldest element.
     * @throws EmptyBufferException
     */
    fun read() : T {
        val e = buffer.removeFirstOrNull()

        if (e != null) {
            return e
        } else {
            throw EmptyBufferException()
        }
    }

    /**
     * Write [e] to the end of the buffer. Throws an exception if the buffer is full.
     * @throws BufferFullException
     */
    fun write(e: T) {
        if (buffer.size < maxSize) {
            buffer.add(e)
        } else {
            throw BufferFullException()
        }
    }

    /**
     * Write [e] to the end of the buffer. If the buffer is full, also drops the oldest element.
     */
    fun overwrite(e: T) {
        buffer.add(e)

        // Aggressively enforce maximum buffer size.
        // It will be impossible for it to be more than one element too long,
        // but guard against it anyway.
        while (buffer.size > maxSize) {
            buffer.removeFirstOrNull()
        }
    }

    /**
     * Empty the buffer.
     */
    fun clear() {
        while (buffer.size > 0) {
            buffer.removeFirstOrNull()
        }
    }
}
