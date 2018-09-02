package ru.ohrana_neva.smsredirector.dialogs

import android.app.Activity
import android.app.Dialog
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog

class DialogAlert {

    fun onCreateDialog(activity: Activity, @StringRes title: Int, @StringRes msg: Int): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
        builder.setMessage(msg)

        builder.setPositiveButton("Ok") { _, _ ->

        }
        return builder.create()
    }
}