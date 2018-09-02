package ru.ohrana_neva.smsredirector.mvp.models

import ru.ohrana_neva.smsredirector.realm.PojoPhone
import ru.ohrana_neva.smsredirector.repository.DataBase
import java.util.ArrayList

class ModelCreate {

    fun addGroup(name: String, phoneSender: String, phones: List<String>) {
        val list = ArrayList<PojoPhone>()
        for (item in phones) {
            val phone = PojoPhone(number = item)
            list.add(phone)
        }
        DataBase.writeGroup(name, phoneSender, list)
    }
}