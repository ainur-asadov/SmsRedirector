package ru.ohrana_neva.smsredirector.mvp.models

import ru.ohrana_neva.smsredirector.realm.Group
import ru.ohrana_neva.smsredirector.repository.DataBase
import java.util.ArrayList

class ModelMain {

    fun getGroups(): ArrayList<Group> {
        DataBase.openDB()
        return DataBase.readGroups()
    }
}