package ru.ohrana_neva.smsredirector.mvp.presenters

import ru.ohrana_neva.smsredirector.R
import ru.ohrana_neva.smsredirector.mvp.BasePresenter
import ru.ohrana_neva.smsredirector.mvp.models.ModelCreate
import ru.ohrana_neva.smsredirector.mvp.view.ViewCreate
import java.util.ArrayList

class PresenterCreate(private val model: ModelCreate): BasePresenter<ViewCreate>() {

    private val passPhones = ArrayList<String>()

    override fun viewReady() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun addPhone(phone: String) {
        when(phone) {
            "" -> getView().showAlert(R.string.title_default, R.string.hint_add_phone)
            else -> {
                passPhones.add(phone)
                getView().showAlert(R.string.title_default, R.string.text_phone_added)
            }
        }
    }

    fun check(groupName: String, phoneSender: String, phonePass: String) {
        when {
            (groupName == "") or (phoneSender == "") or (phonePass == "" && passPhones.size == 0) ->
                getView().showAlert(R.string.title_default, R.string.hint_error_save_group)
            else -> {
                if (phonePass != "") {
                    passPhones.add(phonePass)
                }
                model.addGroup(groupName, phoneSender, passPhones)
                getView().showAlert(R.string.title_default, R.string.text_group_added)
                getView().reset()
            }
        }
    }
}