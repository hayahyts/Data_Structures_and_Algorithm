import stacks.Node

/**
 * Implementation of Singly-Linked List
 */
class LinkedList<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var length: Int = 0

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
        if (shouldInitInstead(value)) return
        val node = stacks.Node(value, null)
        tail?.next = node
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
        if (shouldInitInstead(value)) return
        val node = stacks.Node(value, head)
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
        verifyIndexWithinRange(index)
        if (shouldInitInstead(value)) return

        val node = stacks.Node(value, null)

        when (index) {
            0 -> prepend(value) // Insert at beginning
            length -> append(value) // Insert at end
            else -> {
                val leadingNode = traverseToIndex(index - 1)
                val endingNode = leadingNode?.next
                leadingNode?.next = node
                node.next = endingNode
                length++
            }
        }
    }

    private fun shouldInitInstead(value: T): Boolean {
        val node = stacks.Node(value, null)
        if (head == null) {
            head = node
            tail = node
            length++
            return true
        }
        return false
    }

    private fun removeFirst() {
        val nextNode = head?.next
        head = nextNode
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
     * Output: stacks.Node(2,next)
     *
     * Algorithm
     * Keep Going until you meet index
     */
    private fun traverseToIndex(index: Int): Node<T>? {
        var counter = 0
        var currentNode: Node<T>? = head

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
        var currentItem: Node<T>? = head

        do {
            val value = currentItem?.value ?: return array
            array.add(value)
            currentItem = currentItem.next
        } while (currentItem != null)

        return array
    }

    /**
     * Given: [1, 2, 3, 4, 5, 6]
     *
     * Output: [6, 5, 4, 3, 2, 1]
     *
     * Tail = head
     * Tail = 1 -> 2
     *
     * 1 -> 2
     * 2 -> 3
     * 3 -> 4
     * 4 -> 5
     * 5 -> 6
     * l=1,r=2
     *
     * =======
     * 1 -> 2
     * 2 -> 1
     * 3 -> 4
     * 4 -> 5
     * 5 -> 6
     * l=2,r=3
     *
     * =======
     * 1 -> 2
     * 2 -> 1
     * 3 -> 2
     * 4 -> 5
     * 5 -> 6
     * l=3,r=4
     *
     * =======
     * 1 -> 2
     * 2 -> 1
     * 3 -> 2
     * 4 -> 3
     * 5 -> 6
     * l=4,r=5
     *
     * =======
     * 1 -> 2
     * 2 -> 1
     * 3 -> 2
     * 4 -> 3
     * 5 -> 4
     * l=5,r=6
     *
     * =======
     * 1 -> 2
     * 2 -> 1
     * 3 -> 2
     * 4 -> 3
     * 5 -> 4
     * 6 -> 5
     * l=6,r=null
     *
     *
     */
    fun reverse4(): LinkedList<T> {
        if (head?.next == null) return this

        var prevNode: Node<T>? = null
        var currNode = head

        while (currNode != null) {
            val nextNode = currNode.next
            currNode.next = prevNode
            prevNode = currNode
            currNode = nextNode
        }

        head = prevNode

        return this
    }

    fun getLength(): Int {
        return length
    }
}

private class Node<T>(val value: T, var next: Node<T>?)

fun <T> printAllItemInLinkedList(list: LinkedList<T>) {
    println("Array items: ${list.toList()}")
}

fun main() {
    val intList = LinkedList<Int>()
    intList.append(1)
    intList.append(2)
    intList.append(3)
    intList.append(4)
    intList.append(5)
    intList.append(6)

    printAllItemInLinkedList(intList.reverse4())

    val strList = LinkedList<String>()
    strList.append("Aryeetey")
    strList.append("Solomon")
    strList.append("Junior")
    // printAllItemInLinkedList(strList)

    val footballList = LinkedList<String>()
    footballList.append("Chelsea")
    footballList.prepend("Barcelona")
    footballList.insert(0, "Real Madrid")
    footballList.insert(footballList.getLength(), "Man U")
    footballList.append("Sporting Lisbon")
    footballList.insert(footballList.getLength() - 1, "Juventus")
    footballList.remove(1)
    // printAllItemInLinkedList(footballList)
    // printAllItemInLinkedList(footballList.reverse())
    //printAllItemInLinkedList(footballList.reverse2())

    val hashSet = HashSet<Int>()
}