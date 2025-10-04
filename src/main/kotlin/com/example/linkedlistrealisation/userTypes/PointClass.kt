package com.example.linkedlistrealisation.userTypes

import com.example.linkedlistrealisation.comparators.PointComparator
import com.example.linkedlistrealisation.interfaces.UserTypeInterface
import com.example.linkedlistrealisation.models.Point


class PointClass : UserTypeInterface, Cloneable {
    override fun typeName(): String {
        return "Point"
    }

    override fun create(): Any {
        return Point((Math.random() * 100).toInt() - 50, (Math.random() * 100).toInt() - 50)
    }

    override fun readValueSer(obj: Any?): String {
        val pointToSer = obj as Point?
        return pointToSer.toString()
    }

    override fun parseValueDeser(ss: String?): Any? {
        val len = ss!!.length
        val substr = ss.substring(1, len - 1)
        val parameterOfPoint = substr.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return if (parameterOfPoint.size == 2) {
            val x = parameterOfPoint[0].toInt()
            val y = parameterOfPoint[1].toInt()
            Point(x, y)
        } else {
            null
        }
    }

    override fun getTypeComparator(): Comparator<Any> {
        return PointComparator() as Comparator<Any>
    }
}
