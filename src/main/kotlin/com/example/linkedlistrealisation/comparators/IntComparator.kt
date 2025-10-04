package com.example.linkedlistrealisation.comparators


class IntComparator : Comparator<Int> {
    override fun compare(o1: Int?, o2: Int?): Int {
        return o1!!.compareTo(o2!!)
    }
}
