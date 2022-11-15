package demo.symple.userapi.utils

class Cache<K, V>(private val maxEntries: Int) : LinkedHashMap<K, V>(maxEntries + 1, 1.0f, true) {
    override fun removeEldestEntry(eldest: Map.Entry<K, V>): Boolean {
        return super.size > maxEntries
    }
}
