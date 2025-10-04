package com.example.linkedlistrealisation.interfaces

interface UserTypeInterface {
    fun typeName(): String?
    fun create(): Any?

    fun readValueSer(obj: Any?): String?
    fun parseValueDeser(ss: String?): Any?

    fun getTypeComparator(): Comparator<Any>?
}