package com.example.linkedlistrealisation.dataStructures

import com.example.linkedlistrealisation.interfaces.LinkedListInterface



class LinkedListRealisation<T> : LinkedListInterface<T>, Iterable<T>{
    private var first: Node? = null
    private var last: Node? = null
    var size = 0
        private set

    fun clear() {
        first = null
        last = null
        size = 0
    }

    override fun add(element: T): Boolean {
        if (size == 0) {
            first = Node(null, null, element)
            last = first
        } else {
            val secondLast: Node? = last
            last = Node(null, secondLast, element)
            if (secondLast != null) {
                secondLast.next = last
            }
        }
        size++
        return true
    }

    override fun addByIndex(id: Int, value: T): Boolean {
        if (id < 0 || id > size) {
            throw IndexOutOfBoundsException()
        }
        if (id == size) {
            add(value)
            return true
        }
        val nodeToReplace: Node? = getNode(id)
        val nodeToReplacePrev: Node? = nodeToReplace?.prev
        val nodeToAdd: Node = Node(nodeToReplace, nodeToReplacePrev, value)
        if (nodeToReplace != null) {
            nodeToReplace.prev = nodeToAdd
        }
        if (nodeToReplacePrev != null) {
            nodeToReplacePrev.next = nodeToAdd
        } else {
            first = nodeToAdd
        }
        size++
        return true
    }

    override fun remove(value: T): Boolean {
        var tempNode: Node? = first
        for (i in 0 until size) {
            if (tempNode != null) {
                if (tempNode.value == value) {
                    return removeByIndex(i)
                }
            }
            if (tempNode != null) {
                tempNode = tempNode.next
            }
        }
        return false
    }

    override fun removeByIndex(id: Int): Boolean {
        val nodeToRemove: Node? = getNode(id)
        val nodeToRemovePrev: Node? = nodeToRemove?.prev
        val nodeToRemoveNext: Node? = nodeToRemove?.next
        if (nodeToRemoveNext != null) {
            nodeToRemoveNext.prev = nodeToRemovePrev
        } else {
            last = nodeToRemovePrev
        }
        if (nodeToRemovePrev != null) {
            nodeToRemovePrev.next = nodeToRemoveNext
        } else {
            first = nodeToRemoveNext
        }
        size--
        return true
    }

    override fun get(id: Int): T {
        return getNode(id)!!.value
    }

    /*    LinkedList<Object> linkedList;
    linkedList*/
    private fun getNode(id: Int): Node? {
        if (id < 0 || id >= size) {
            throw IndexOutOfBoundsException()
        }
        var tempNode: Node? = first
        for (i in 0 until id) {
            if (tempNode != null) {
                tempNode = tempNode.next
            }
        }
        return tempNode
    }

    fun getFirst(): Node? {
        return first
    }

    // возвращаем середину списка
    private fun split(head: Node): Node? {
        var fast: Node = head
        var slow: Node = head
        while (fast.next != null
                && fast.next!!.next != null) {
            fast = fast.next!!.next!!
            slow = slow.next!!
        }
        val temp: Node? = slow.next
        slow.next = null
        return temp
    }

    fun sort(comparator: Comparator<Any>): LinkedListRealisation<T> {
        if(first == null){
            return this
        }
        val node: Node? = mergeSort(first, comparator)
        first = node
        var cur = first
        while(cur?.next != null){
            last = cur.next
            cur = cur.next
        }
        return this
    }

    fun mergeSort(node: Node?, comparator: Comparator<Any>): Node? {
        var node: Node? = node
        if (node?.next == null) {
            return node
        }
        var second: Node? = split(node)

        // проходим рекурсивно по частям списка
        node = mergeSort(node, comparator)
        second = mergeSort(second, comparator)

        // Сливаем части воедино
        return merge(node, second, comparator)
    }

    // Функция слияния
    private fun merge(first: Node?, second: Node?, comparator: Comparator<Any>): Node? {
        if (first == null) {
            return second
        }
        if (second == null) {
            return first
        }

        // Выбираем меньшее значение
        return if (comparator.compare(first.value, second.value) < 0) {
            first.next = merge(first.next, second, comparator)
            first.next?.prev = first
            first.prev = null
            first
        } else {
            second.next = merge(first, second.next, comparator)
            second.next?.prev = second
            second.prev = null
            second
        }
    }


    inner class Node(next: Node?, prev: Node?, value: T) {
        var next: Node?
        var prev: Node?
        var value: T

        init {
            this.next = next
            this.prev = prev
            this.value = value
        }
    }

    override fun iterator(): Iterator<T> = object :Iterator<T> {
        var node = first

        override fun hasNext(): Boolean {
            return node != null
        }

        override fun next(): T {
            val elem = node!!.value
            node = node?.next
            return elem
        }
    }

}

