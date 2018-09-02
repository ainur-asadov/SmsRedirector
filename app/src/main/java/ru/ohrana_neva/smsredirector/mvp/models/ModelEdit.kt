package ru.ohrana_neva.smsredirector.mvp.models

import ru.ohrana_neva.smsredirector.repository.DataBase

class ModelEdit {

    fun getGroupByName(name: String) = DataBase.readGroupByName(name)

    fun deleteGroup(name: String) {
        DataBase.removeGroupByName(name)
    }

    fun reWriteData(currentName: String, name: String, phone: String) {
        DataBase.changeGroupData(currentName, name, phone)
    }

    fun deletePhone(name: String, phone: String) {
        DataBase.removePhone(name, phone)
    }

    fun addPhone(name: String, phone: String) {
        DataBase.addPassPhone(name, phone)
    }
}