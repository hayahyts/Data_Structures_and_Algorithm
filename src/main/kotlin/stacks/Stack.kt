package stacks

class Stack<T> {
    var top: Node<T>? = null
    var bottom: Node<T>? = null
    var length: Int = 0

    fun push(value: T) { // O(n)
        if (top == null) {
            bottom = Node(value, null)
            top = bottom
            length++
            return
        }

        val newNode = Node(value, null)
        top?.next = newNode
        top = newNode
        length++
    }

    fun peek(): T? {
        return top?.value
    }

    fun pop(): T? {
        if (bottom?.next == null) {
            val value = bottom?.value
            bottom = null
            return value
        }

        var currentNode = bottom
        while (currentNode?.next?.next !=null){
            currentNode = currentNode.next
        }

        val poppedValue = currentNode?.next?.value
        top = currentNode
        top?.next = null
        return  poppedValue
    }

    fun isEmpty(): Boolean {
        return bottom == null
    }

    fun hasMore(): Boolean {
        return bottom != null
    }
}

class Node<T>(val value: T, var next: Node<T>?)

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
}