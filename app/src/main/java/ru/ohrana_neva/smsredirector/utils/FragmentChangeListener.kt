package ru.ohrana_neva.smsredirector.utils

import android.support.annotation.StringRes

interface FragmentChangeListener {

    fun setTitle(@StringRes title: Int)
    fun showCreateFragment()
    fun showExpandFragment(name: String)
    fun showEditFragment(groupName: String)
    fun returnToStartStack()
}