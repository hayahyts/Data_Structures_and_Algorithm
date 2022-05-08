class HashTable(size: Int) {
    private var data: Array<ArrayList<Array<Any>>>

    init {
        if (size < 1) throw Exception("Size must be greater than 0")
        data = Array(size) { arrayListOf() }
    }

    private fun hash(key: String): Int {
        var hash = 0
        for (i in key.indices) {
            hash = (hash + key[i].code * i) % data.size
        }
        return hash
    }

    fun set(key: String, value: Any) {
        val position = hash(key)
        val pair = Array(2) { Any() }
        pair[0] = key
        pair[1] = value
        data[position].add(pair)
    }

    fun get(key: String): Any? {
        val bucket = data[hash(key)]
        for (element in bucket) {
            if (element[0] == key) {
                return element[1]
            }
        }
        return null
    }
}

fun main() {
    val key = "grapes"
    val key2 = "orange"
    val key3 = "hatred"

    val hashTable = HashTable(1)
    hashTable.set(key, 10000)
    hashTable.set(key2, "Wow")

    println("Value for $key is ${hashTable.get(key)}")
    println("Value for $key2 is ${hashTable.get(key2)}")
    println("Value for $key3 is ${hashTable.get(key3)}")
}