object GroupAnagrams {

    fun groupAnagrams(strArr: Array<String>): List<List<String>> {
        val groupAnagrams = HashMap<String, ArrayList<String>>()
        for (str in strArr) { // n
            val numOfEachChar = Array(26) { 0 }
            for (char in str) {
                val pos = char - 'a'
                numOfEachChar[pos] = numOfEachChar[pos] + 1
            }

            val key = getKeyForArray(numOfEachChar)
            if (!groupAnagrams.containsKey(key)) {
                groupAnagrams[key] = ArrayList()
            }
            groupAnagrams[key]?.add(str)
        }

        val result = ArrayList<ArrayList<String>>()
        for (element in groupAnagrams) {
            val anagrams = element.value
            result.add(anagrams)
        }


        return result
    }

    private fun getKeyForArray(numOfEachChar: Array<Int>): String {
        val key = StringBuilder()
        for ((i, num) in numOfEachChar.withIndex()) {
            key.append("${'a'.plus(i)}$num")
        }
        return key.toString()
    }
}

fun main() {
    val inputArr = arrayOf("eat", "tea", "tan", "ate", "nat", "bat")
    println(GroupAnagrams.groupAnagrams(inputArr))
}