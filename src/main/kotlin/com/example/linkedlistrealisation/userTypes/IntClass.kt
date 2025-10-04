package com.example.linkedlistrealisation.userTypes

import com.example.linkedlistrealisation.comparators.IntComparator
import com.example.linkedlistrealisation.interfaces.UserTypeInterface


class IntClass : UserTypeInterface {
    override fun typeName(): String {
        return "Integer"
    }

    override fun create(): Any {
        return (Math.random() * 100).toInt() - 50
    }

    override fun readValueSer(obj: Any?): String {
        return obj.toString()
    }

    override fun parseValueDeser(ss: String?): Int {
        return ss!!.toInt()
    }

    override fun getTypeComparator(): Comparator<Any> {
        return IntComparator() as Comparator<Any>
    }
}
