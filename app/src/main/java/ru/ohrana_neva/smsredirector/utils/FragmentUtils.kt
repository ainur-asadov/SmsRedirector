package ru.ohrana_neva.smsredirector.utils

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

//вычищаем backstack и добавлям новый фрагмент
fun FragmentActivity.changeFragmentWithoutBackStack(fragment: Fragment, @IdRes id: Int) {
    val fm = supportFragmentManager
    if (fm.fragments != null) {
        for (item in fm.fragments) {
            if (item != null) {
                fm.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        }
    }

    fm.transact {
        //setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        replace(id, fragment, fragment.javaClass.name)
    }
}



//отображение фрагмента с добавление с stack
fun FragmentActivity.changeFragment(fragment: Fragment, @IdRes id: Int) {
    supportFragmentManager.findFragmentById(id)?.onHiddenChanged(true)
    supportFragmentManager.transact {
        //setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
        replace(id, fragment, fragment.javaClass.name)
        addToBackStack(fragment.javaClass.name)
    }
}

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commitAllowingStateLoss()
}
