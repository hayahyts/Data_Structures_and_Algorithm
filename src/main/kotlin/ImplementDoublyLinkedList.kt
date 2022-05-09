class DoublyLinkedList<T>(value: T) {
    private var head: Node2<T>?
    private var tail: Node2<T>?
    private var length: Int

    init {
        val node = Node2(value, null, null)
        head = node
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
     * [address 1] = {v:2,p:null,n:[address 3]}
     * [address 3] = {v:2,p:[address 1],n:[address 5]}
     * [address 5] = {v:2,p:[address 3],n:null}
     *
     * To append 5 at the end,
     * Save 5 at [address 7]
     * Let next of tail point to new address [address 7]
     * Now set new address as tail of list
     * [address 1] = {v:2,p:null,n:[address 3]}
     * [address 3] = {v:2,p:[address 1],n:[address 5]}
     * [address 5] = {v:2,p:[address 3],n:[address 7]}
     * [address 7] = {v:5,p:[address 5],n:null}
     */
    fun append(value: T) {
        val node = Node2(value, null, null)
        tail?.next = node
        node.previous = tail
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
        val node = Node2(value, null, null)
        head?.previous = node
        node.next = head
        head = node
        length++
    }

    /**
     * Given a list [2,3,4] insert value 1 at position 1
     */
    fun insert(index: Int, value: T) {
        verifyIndexWithinRange(index)
        val node = Node2(value, null, null)

        when (index) {
            0 -> prepend(value) // Insert at beginning
            length -> append(value) // Insert at end
            else -> {
                val leadingNode = traverseToIndex(index - 1)
                val endingNode = leadingNode?.next
                leadingNode?.next = node
                endingNode?.previous = node
                node.next = endingNode
                node.previous = leadingNode
                length++
            }
        }
    }

    private fun removeFirst() {
        val newHead = head?.next
        newHead?.previous = null
        head = newHead
        length--
    }

    fun remove(index: Int) {
        verifyIndexWithinRange(index)
        when (index) {
            0 -> removeFirst()
            else -> {
                val leadingNode = traverseToIndex(index - 1)
                val nodeToRemove = leadingNode?.next
                val endingNode = nodeToRemove?.next
                leadingNode?.next = endingNode
                endingNode?.previous = leadingNode
            }
        }
    }

    private fun verifyIndexWithinRange(index: Int) {
        if (index < 0) throw IndexOutOfBoundsException("Index must be greater than or equal to 0")
        if (index > length) throw java.lang.IndexOutOfBoundsException("Index must be less than or equal to length of list")
    }

    /**
     * Traverse the list to a given index
     *
     * Given: [2,3,4,5], index:2
     *
     * Output: Node(2,previous,next)
     *
     * Algorithm
     * Keep Going until you meet index
     */
    private fun traverseToIndex(index: Int): Node2<T>? {
        var counter = 0
        var currentNode: Node2<T>? = head

        while (counter != index) {
            currentNode = currentNode?.next ?: break
            counter++
        }
        return currentNode
    }

    /**
     * LinkedList traversal
     */
    fun toList(): ArrayList<T> {
        val array = ArrayList<T>()
        var currentItem: Node2<T>? = head

        while (currentItem != null) {
            array.add(currentItem.value)
            currentItem = currentItem.next
        }

        return array
    }

    fun getLength(): Int {
        return length
    }
}

class Node2<T>(
    val value: T,
    var next: Node2<T>?,
    var previous: Node2<T>?
)

fun <T> printAllItemInLinkedList(list: DoublyLinkedList<T>) {
    println("Array items: ${list.toList()}")
}

fun main() {
    val intList = DoublyLinkedList(1)
    println(intList.toList())
    intList.append(3)
    println(intList.toList())
    intList.append(5)
    println(intList.toList())
    intList.prepend(-1)
    println(intList.toList())

    val footballList = DoublyLinkedList("Chelsea")
    printAllItemInLinkedList(footballList)
    footballList.prepend("Barcelona")
    printAllItemInLinkedList(footballList)
    footballList.insert(0, "Real Madrid")
    printAllItemInLinkedList(footballList)
    footballList.insert(footballList.getLength(), "Man U")
    printAllItemInLinkedList(footballList)
    footballList.append("Sporting Lisbon")
    printAllItemInLinkedList(footballList)
    footballList.insert(footballList.getLength() - 1, "Juventus")
    printAllItemInLinkedList(footballList)
    footballList.remove(1)
    printAllItemInLinkedList(footballList)
}