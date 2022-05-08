fun combine2SortedArrays(arr1: IntArray, arr2: IntArray): IntArray { // Big O(a + b)
    var pos = 0 // Current position of new array
    var i = 0 // Set index for array 1
    var j = 0 // Set index for array 2
    val newArraySize = arr1.size + arr2.size
    val newArray = IntArray(newArraySize)

    while (pos < newArraySize) {
        if (i >= arr1.size && j < arr2.size) {
            newArray[pos] = arr2[j++]
        } else if (j >= arr2.size && i < arr1.size) {
            newArray[pos] = arr1[i++]
        } else if (arr1[i] < arr2[j]) {
            newArray[pos] = arr1[i++]
        } else {
            newArray[pos] = arr2[j++]
        }

        pos++
    }

    return newArray
}

fun main() {
    val arr1 =intArrayOf(1,4,5,6)
    val arr2 = intArrayOf(2, 3, 7, 8)
    val arr = combine2SortedArrays(arr1, arr2)
    println(arr.contentToString())
}