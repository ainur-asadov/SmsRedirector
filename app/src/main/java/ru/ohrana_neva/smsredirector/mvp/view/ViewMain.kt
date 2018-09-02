package ru.ohrana_neva.smsredirector.mvp.view

import ru.ohrana_neva.smsredirector.mvp.MvpView
import ru.ohrana_neva.smsredirector.realm.Group

interface ViewMain : MvpView {

    fun showList(list: List<Group>)
    fun showConfirmAddGroup()
    fun showExpand(groupName: String)
}