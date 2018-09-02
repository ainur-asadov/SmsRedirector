package ru.ohrana_neva.smsredirector.mvp

import android.support.annotation.StringRes

interface MvpView {

    fun showAlert(@StringRes title: Int, @StringRes msg: Int)
}