package ru.ohrana_neva.smsredirector.mvp.presenters

import ru.ohrana_neva.smsredirector.R
import ru.ohrana_neva.smsredirector.mvp.BasePresenter
import ru.ohrana_neva.smsredirector.mvp.models.ModelEdit
import ru.ohrana_neva.smsredirector.mvp.view.ViewEdit

class PresenterEdit(private val model: ModelEdit) : BasePresenter<ViewEdit>() {

    lateinit var groupName: String
    lateinit var phone: String

    override fun viewReady() {
        val group = model.getGroupByName(groupName)
        getView().setData(group!!.name!!, group.phone!!)
        getView().showList(group)
    }

    fun deleteGroup() {
        model.deleteGroup(groupName)
    }

    fun deletePhone(phone: String) {
        model.deletePhone(groupName, phone)
        getView().showAlert(R.string.title_default, R.string.text_data_changed)
        getView().showList(model.getGroupByName(groupName))
    }

    fun changeData(name: String, phone: String) {
        when {
            (name == "") or (phone == "") -> getView().showAlert(R.string.title_default, R.string.hint_error_save_group)
            (name == groupName) && (phone == this.phone) -> {}
            else -> {
                model.reWriteData(groupName, name, phone)
                getView().showAlert(R.string.title_default, R.string.text_data_changed)
            }
        }
    }

    fun addPhone(phone: String) {
        when(phone) {
            "" -> getView().showAlert(R.string.title_default, R.string.hint_error_save_group)
            else -> {
                model.addPhone(groupName, phone)
                getView().showList(model.getGroupByName(groupName))
                getView().reset()
                getView().showAlert(R.string.title_default, R.string.text_phone_added)
            }
        }
    }
}