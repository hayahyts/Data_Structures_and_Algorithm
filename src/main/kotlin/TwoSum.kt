fun main() {
    val array = intArrayOf(3, 2, 4)
    println(twoSum(array, 6).contentToString())
}

fun twoSum(array: IntArray, sum: Int): IntArray {
    val hashMap = HashMap<Int, Int>() // (Element,position)
    hashMap[array.first()] = 0

    for ((i, element) in array.withIndex()) {
        if (i == 0) continue

        val compliment = sum - element
        val complimentIndex = hashMap[compliment] ?: -1
        if (complimentIndex != -1) return intArrayOf(complimentIndex, i)
        hashMap[element] = i
    }

    return intArrayOf()
}