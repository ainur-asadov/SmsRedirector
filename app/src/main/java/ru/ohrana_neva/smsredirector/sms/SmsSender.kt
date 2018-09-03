package ru.ohrana_neva.smsredirector.sms

import android.telephony.SmsManager
import ru.ohrana_neva.smsredirector.repository.DataBase

class SmsSender {

    fun send(phoneSender: String, text: String) {
        val groups = DataBase.readGroups()
        groups.forEach {
            if (it.phone == phoneSender) {
                it.phones?.forEach { phone ->
                    SmsManager.getDefault().sendTextMessage(phone.number, null, text, null, null)
                }
            }
        }
    }
}