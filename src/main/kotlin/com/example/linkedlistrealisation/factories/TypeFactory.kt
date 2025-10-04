package com.example.linkedlistrealisation.factories

import com.example.linkedlistrealisation.interfaces.UserTypeInterface
import com.example.linkedlistrealisation.userTypes.IntClass
import com.example.linkedlistrealisation.userTypes.PointClass


class TypeFactory {
    private val list = ArrayList<UserTypeInterface?>()

    init {
        list.add(IntClass())
        list.add(PointClass())
    }

    val typeNameList: ArrayList<String?>
        get() {
            val typeNameList = list.stream().map { obj: UserTypeInterface? -> obj!!.typeName() }.toList()
            return ArrayList(typeNameList)
        }

    fun getBuilderByName(name: String): UserTypeInterface? {
        return list.stream().filter { userTypeInterface: UserTypeInterface? -> userTypeInterface!!.typeName() == name }
                .findAny().orElse(null)
    }
}

