package com.example.linkedlistrealisation.models

import java.io.Serializable


class Point(var x: Int, var y: Int) : Serializable {

    override fun toString(): String {
        return "(" +
                x +
                ", " + y +
                ')'
    }
}
