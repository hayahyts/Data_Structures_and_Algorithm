object IsAnagram {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val map = mutableMapOf<Char, Int>()

        for (i in s.indices) {
            map[s[i]] = (map[s[i]] ?: 0) + 1
            map[t[i]] = (map[t[i]] ?: 0) - 1
            if (map[s[i]] == 0) map.remove(s[i])
            if (map[t[i]] == 0) map.remove(t[i])
            println(map.toString())
        }

        return map.isEmpty()
    }
}

fun main() {
    val s = "anagram"
    val t = "nagaram"
    println("Is anagram: ${IsAnagram.isAnagram(s, t)}")
}