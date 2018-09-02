package ru.ohrana_neva.smsredirector.mvp.view

import ru.ohrana_neva.smsredirector.mvp.MvpView
import ru.ohrana_neva.smsredirector.realm.Group

interface ViewEdit : MvpView {

    fun showList(group: Group?)
    fun setData(name: String, phone: String)
    fun reset()
}