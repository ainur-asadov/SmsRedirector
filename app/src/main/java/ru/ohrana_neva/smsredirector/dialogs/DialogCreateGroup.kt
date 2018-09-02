package ru.ohrana_neva.smsredirector.dialogs

import android.app.Activity
import android.app.Dialog
import android.support.v7.app.AlertDialog
import ru.ohrana_neva.smsredirector.R

class DialogCreateGroup {

    fun onCreateDialog(activity: Activity): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.title_no_group)
        builder.setMessage(R.string.question_add_group)

        builder.setPositiveButton(R.string.text_add) { _, _ ->

        }

        builder.setNegativeButton(R.string.text_cancel) { _, _ ->

        }
        return builder.create()
    }
}