package demo.symple.userapi.utils

import java.util.LinkedHashMap

internal class LruCache<A, B>(private val maxEntries: Int) : LinkedHashMap<A, B>(maxEntries + 1, 1.0f, true) {
    override fun removeEldestEntry(eldest: Map.Entry<A, B>): Boolean {
        return super.size > maxEntries
    }
}
