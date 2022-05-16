package stacks

/**
 * Stacks use Linked List pointing downwards
 *
 * null <- a <- b <- c
 */
class Stack<T> {
    private var top: Node<T>? = null
    private var bottom: Node<T>? = null
    private var length: Int = 0

    fun push(value: T) { // O(n)
        val newNode = Node(value)
        if (top == null) {
            bottom = newNode
            top = bottom
        } else {
            val holdingPointer = top
            top = newNode
            top?.next = holdingPointer
        }
        length++
    }

    fun peek(): T? {
        return top?.value
    }

    /**
     * Replace the last but one item as top
     */
    fun pop(): T? {
        if (top == null) {
            return null
        }

        val holdingPointer = top
        if (top == bottom) {
            bottom = null
        }
        top = holdingPointer?.next
        return holdingPointer?.value
    }

    fun isEmpty(): Boolean {
        return bottom == null
    }

    fun hasMore(): Boolean {
        return bottom != null
    }
}

/**
 * Stack which uses Arrays
 */
class Stack2<T> {
    private val items = ArrayList<T>()

    fun push(item: T) {
        items.add(item)
    }

    fun pop(): T? {
        if (items.size < 1) return null
        val lastPos = items.size - 1
        val lastItem = items[lastPos]
        items.removeAt(lastPos)
        return lastItem
    }

    fun peek(): T? {
        if (items.size < 1) return null
        val lastPos = items.size - 1
        return items[lastPos]
    }

    fun hasMore(): Boolean {
        return (items.size >= 1)
    }
}

class Node<T>(val value: T, var next: Node<T>? = null)

fun main() {
    val browserHistory = Stack<String>()
    browserHistory.apply {
        push("A.com")
        push("B.com")
        push("C.com")
    }

    while (browserHistory.hasMore()) {
        println(browserHistory.pop())
    }

    println("=======================")
    val browserHistory2 = Stack2<String>()
    browserHistory2.apply {
        push("A.com")
        push("B.com")
        push("C.com")
    }

    while (browserHistory2.hasMore()) {
        println(browserHistory2.pop())
    }
}