fun firstRecurringCharacter(array: IntArray): Int? {
    val hashMap = HashSet<Int>()
    for (element in array) {
        if (hashMap.contains(element)) return element
        hashMap.add(element)
    }

    return null
}

fun printRecurringCharacterForArray(array: IntArray) {
    println("First recurring character for array: $array is ${firstRecurringCharacter(array)}")
}

fun main() {
    val array1 = intArrayOf(2, 5, 1, 2, 3, 5, 1, 2, 4)
    val array2 = intArrayOf(2, 1, 1, 2, 3, 5, 1, 2, 4)
    val array3 = intArrayOf(2, 3, 4, 5)

    printRecurringCharacterForArray(array1)
    printRecurringCharacterForArray(array2)
    printRecurringCharacterForArray(array3)
}