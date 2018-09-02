package ru.ohrana_neva.smsredirector.mvp

interface MvpPresenter<V : MvpView> {

    fun attachView(view: V)
    fun viewReady()
    fun getView(): V
    fun detachView()
}