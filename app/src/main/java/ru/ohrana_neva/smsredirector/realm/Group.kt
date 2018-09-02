package ru.ohrana_neva.smsredirector.realm

import io.realm.RealmList
import io.realm.RealmObject

open class Group(var name: String? = null, var phone: String? = null, var phones: RealmList<Phone>? = null) : RealmObject()