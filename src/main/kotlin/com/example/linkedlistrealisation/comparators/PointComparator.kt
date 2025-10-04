package com.example.linkedlistrealisation.comparators

import com.example.linkedlistrealisation.models.Point
import kotlin.math.pow
import kotlin.math.sqrt


open class PointComparator : Comparator<Point> {
    override fun compare(o1: Point, o2: Point): Int {
        return sqrt((o1.x.toDouble().pow(2.0).toInt() + o1.y.toDouble().pow(2.0).toInt()).toDouble()).compareTo(sqrt((o2.x.toDouble().pow(2.0).toInt() + Math.pow(o2.y.toDouble(), 2.0).toInt()).toDouble()).toInt().toDouble())
    }
}
