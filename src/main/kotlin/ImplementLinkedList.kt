class LinkedList<T>(value: T) {
    private var head: Node<T>
    private var tail: Node<T>
    private var length: Int

    init {
        head = Node(value, null)
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
        val node = Node(value, null)
        tail.next = node
        tail = node
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
        val node = Node(value, head)
        head = node
        length++
    }

    /**
     * Given a list [2,3,4] insert value 1 at position 1
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
    fun insert(index: Int, value: T) {
        if (index < 0) throw IndexOutOfBoundsException("Index must be greater than or equal to 0")
        if (index > length) throw java.lang.IndexOutOfBoundsException("Index must be less than or equal to length of list")

        val node = Node(value, null)

        when (index) {
            0 -> prepend(value) // Insert at beginning
            length -> append(value) // Insert at end
            else -> {
                val leadingNode = traverseToIndex(index - 1)
                val endingNode = leadingNode.next
                leadingNode.next = node
                node.next = endingNode
                length++
            }
        }
    }

    /**
     * Traverse the list to a given index
     *
     * Given: [2,3,4,5], index:2
     *
     * Output: Node(2,next)
     *
     * Algorithm
     * Keep Going until you meet index
     */
    private fun traverseToIndex(index: Int): Node<T> {
        var counter = 0
        var currentNode: Node<T> = head

        while (counter < index) {
            currentNode = currentNode.next ?: break
            counter++
        }
        return currentNode
    }

    /**
     * LinkedList traversal
     */
    fun toList(): ArrayList<T> {
        val array = ArrayList<T>()
        var currentItem: Node<T>? = head

        do {
            val value = currentItem?.value ?: return array
            array.add(value)
            currentItem = currentItem.next
        } while (currentItem != null)

        return array
    }

    fun getLength(): Int {
        return length
    }
}

class Node<T>(val value: T, var next: Node<T>?)

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
    footballList.prepend("Barcelona")
    footballList.insert(0, "Real Madrid")
    footballList.insert(footballList.getLength(), "Man U")
    footballList.append("Sporting Lisbon")
    footballList.insert(footballList.getLength() - 1, "Juventus")
    printAllItemInLinkedList(footballList)
}