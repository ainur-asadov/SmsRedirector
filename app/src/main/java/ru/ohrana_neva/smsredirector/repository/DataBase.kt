package ru.ohrana_neva.smsredirector.repository

import android.annotation.SuppressLint
import io.realm.Realm
import ru.ohrana_neva.smsredirector.console
import ru.ohrana_neva.smsredirector.realm.Group
import ru.ohrana_neva.smsredirector.realm.Phone
import ru.ohrana_neva.smsredirector.realm.PojoPhone
import java.util.ArrayList

object DataBase {

    @SuppressLint("StaticFieldLeak")
    private var realm: Realm = Realm.getDefaultInstance()

    fun openDB() {
        if (realm.isClosed) {
            console(text = "openDB")
            realm = Realm.getDefaultInstance()
        }
    }

    fun writeGroup(name: String, senderPhone: String, list: List<PojoPhone>) {
        realm.beginTransaction()
        val group = realm.createObject(Group::class.java)
        group.name = name
        group.phone = senderPhone
        for (phone in list) {
            val number = realm.createObject(Phone::class.java)
            number.number = phone.number
            group.phones?.add(number)
        }
        realm.commitTransaction()
    }

    fun readGroups() = realm.where(Group::class.java).findAll().toCollection(ArrayList())

    fun readGroupByName(name: String) = realm.where(Group::class.java).equalTo("name", name).findFirst()

    fun removeGroupByName(name: String) {
        realm.beginTransaction()
        realm.where(Group::class.java).equalTo("name", name).findFirst()?.deleteFromRealm()
        realm.commitTransaction()
    }

    fun removePhone(name: String, phone: String) {
        realm.beginTransaction()
        val group = realm.where(Group::class.java).equalTo("name", name).findFirst()
        group?.phones?.forEach { if (it.number == phone) it.deleteFromRealm() }
        realm.commitTransaction()
    }

    fun addPassPhone(name: String, phone: String) {
        realm.beginTransaction()
        val group = realm.where(Group::class.java).equalTo("name", name).findFirst()
        val number = realm.createObject(Phone::class.java)
        number.number = phone
        group?.phones?.add(number)
        realm.commitTransaction()
    }

    fun changeGroupData(currentName: String, name: String, phone: String) {
        realm.executeTransaction { realm ->
            val group = realm.where(Group::class.java).equalTo("name", currentName).findFirst()
            group?.name = name
            group?.phone = phone
        }
    }

    fun closeDB() {
        realm.close()
        console(text = "closeDB")
    }
}