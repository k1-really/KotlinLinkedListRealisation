package com.example.linkedlistrealisation.interfaces


interface LinkedListInterface<T> {
    fun add(element: T): Boolean
    fun addByIndex(id: Int, element: T): Boolean
    fun remove(element: T): Boolean
    fun removeByIndex(id: Int): Boolean
    operator fun get(id: Int): T
}
