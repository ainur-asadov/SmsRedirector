package ru.ohrana_neva.smsredirector.realm

import io.realm.RealmObject

open class Phone(var name: String? = null, var number: String? = null) : RealmObject()