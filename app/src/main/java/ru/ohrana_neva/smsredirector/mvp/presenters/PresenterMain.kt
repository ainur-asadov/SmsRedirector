package ru.ohrana_neva.smsredirector.mvp.presenters

import ru.ohrana_neva.smsredirector.mvp.BasePresenter
import ru.ohrana_neva.smsredirector.mvp.models.ModelMain
import ru.ohrana_neva.smsredirector.mvp.view.ViewMain

class PresenterMain(private val model: ModelMain) : BasePresenter<ViewMain>() {

    override fun viewReady() {
        val groups = model.getGroups()

        when(groups.isEmpty()) {
            true -> getView().showConfirmAddGroup()
            else -> getView().showList(groups)
        }
    }

    fun goToExpandable(groupName: String) {
        getView().showExpand(groupName)
    }
}