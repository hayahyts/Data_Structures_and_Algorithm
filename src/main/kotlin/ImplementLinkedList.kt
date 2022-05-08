class LinkedList<T>(value: T) {
    private var head: Data<T>
    private var tail: Data<T>
    var length: Int

    init {
        head = Data(value, null)
        tail = head
        length = 1
    }

    /**
     * Given a list append value at the end
     *
     * Example:
     * Given: [2,3,4], append 5 at the end
     *
     * Output: [2,3,4,5]
     *
     * Algorithm
     *
     * Current memory
     * [address 1] = {v:2,n:[address 3]}
     * [address 3] = {v:2,n:[address 5]}
     * [address 5] = {v:2,n:null}
     *
     * To append 5 at the end,
     * Save 5 at [address 7]
     * Let next of tail point to new address [address 7]
     * Now set new address as tail of list
     * [address 1] = {v:2,n:[address 3]}
     * [address 3] = {v:3,n:[address 5]}
     * [address 5] = {v:4,n:[address 7]}
     * [address 7] = {v:5, n:null}
     *
     */
    fun append(value: T) {
        val data = Data(value, null)
        tail.next = data
        tail = data
        length++
    }

    /**
     * Given a list append value at the beginning
     *
     * Example:
     * Given: [2,3,4], append 1 at the beginning
     *
     * Output: [1,2,3,4]
     *
     * Create new Data with next pointing to the current head
     * Make this new Data the head of the list
     */
    fun prepend(value: T) {
        val data = Data(value, head)
        head = data
        length++
    }

    /**
     * Given a list [2,3,4] append value 1 at position 1
     *
     * Initial memory structure
     * [address 1] = {v:2, n:[address 3]}
     * [address 3] = {v:3, n:[address 5]}
     * [address 5] = {v:3, n:null]}
     *
     * Create [new data] at address 7
     * [address 7] = {v:1, n:null}
     *
     * Start from head, while there are more data in the list and value not found,
     * Set next item to current item
     * If the value of the current index is equal to provided position,
     * Let next of [new data] point to the current item
     * [address 7] = {v:1, n:[address 1]}
     *
     * We want to keep track of head so if position is 1, then set head to [address 1]
     * head = [address 7]
     *
     * We want to keep track of tail too, so if position == last position of list + 1
     * tail = [address 7]
     */
    fun append(value: T, index: Int) {
        if (index < 0) throw IndexOutOfBoundsException("Index must be greater than or equal to 0")
        if (index > length) throw java.lang.IndexOutOfBoundsException("Index must be less than or equal to length of list")

        val data = Data(value, null)

        when (index) {
            0 -> { // Append at start
                data.next = head
                head = data
                length++
            }
            length -> { // Append at end
                append(value)
            }
            else -> {
                var previousItem: Data<T>? = head
                var currentItem: Data<T>? = previousItem?.next
                var currentIndex = 1
                do {
                    if (currentIndex == index) {
                        previousItem?.next = data
                        data.next = currentItem
                    }

                    previousItem = currentItem
                    currentItem = currentItem?.next
                    currentIndex++
                } while (currentIndex <= index)
                length++
            }
        }
    }

    /**
     * LinkedList traversal
     */
    fun toList(): ArrayList<T> {
        val array = ArrayList<T>()
        var currentItem: Data<T>? = head

        do {
            val value = currentItem?.value ?: return array
            array.add(value)
            currentItem = currentItem.next
        } while (currentItem != null)

        return array
    }
}

class Data<T>(val value: T, var next: Data<T>?)

fun <T> printAllItemInLinkedList(list: LinkedList<T>) {
    println("Array items: ${list.toList()}")
}

fun main() {
    val intList = LinkedList(1)
    intList.append(3)
    intList.append(5)
    printAllItemInLinkedList(intList)

    val strList = LinkedList("Aryeetey")
    strList.append("Solomon")
    strList.append("Junior")
    printAllItemInLinkedList(strList)

    val footballList = LinkedList("Chelsea")
    footballList.append("Real Madrid", 0)
    footballList.append("Man U", footballList.length)
    footballList.append("Sporting Lisbon")
    footballList.append("Juventus", footballList.length - 1)
    printAllItemInLinkedList(footballList)
}