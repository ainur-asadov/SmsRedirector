package ru.ohrana_neva.smsredirector.mvp.models

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ru.ohrana_neva.smsredirector.realm.Group

class MyViewModel : ViewModel() {
    var data: MutableLiveData<Group>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
            }
            return field
        }
}