import kotlin.math.max

object Solution {
    /**
     * Kadane's algorithm
     * 1. Move to an index
     * 2. Check if we should add the element at this index to the previous list OR START FROM THIS INDEX
     */
    fun maxSubArray(numbers: IntArray): Int {
        var maxGlobal = numbers.first()
        var maxCurrent = numbers.first()
        for (i in 1 until numbers.size) {
            maxCurrent = max(numbers[i], (maxCurrent + numbers[i]))
            if (maxCurrent > maxGlobal) maxGlobal = maxCurrent
        }
        return maxGlobal
    }

    fun maxSubArrayPoor(numbers: IntArray): Int {
        var maxSum = 0
        for (i in numbers.indices) {
            var sum = 0
            for (j in i until numbers.size) {
                sum += numbers[j]
                if (maxSum < sum) maxSum = sum
            }
        }

        return maxSum
    }
}

fun main() {
    val array = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
    println(Solution.maxSubArrayPoor(array))
    println(Solution.maxSubArray(array))
    /*
     i=1; c=1;  g=1
     i=2; c=-2; g=1
     i=3; c=4;  g=4
     i=4; c=3;  g=4
     i=5; c=5;  g=5
     i=6; c=6;  g=6
     i=7; c=1;  g=6
     i=8; c=5;  g=6
     */
}