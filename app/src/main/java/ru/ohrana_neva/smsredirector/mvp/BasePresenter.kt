package ru.ohrana_neva.smsredirector.mvp

abstract class BasePresenter<V : MvpView> : MvpPresenter<V> {

    private var v: V? = null

    override fun attachView(view: V) {
        v = view
    }

    override fun getView(): V {
        return v!!
    }

    override fun detachView() {
        v = null
    }
}