package ru.ohrana_neva.smsredirector.sms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony

class MessageReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            var phoneSender = ""
            var smsBody = ""

            for (smsMessage in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                phoneSender = smsMessage.displayOriginatingAddress
                smsBody = smsMessage.messageBody
               /* Log.d("---My Log---", "smsSender: $phoneSender")
                Log.d("---My Log---", "body: $smsBody")*/
            }

            if ((phoneSender != "") && (smsBody != ""))
                SmsSender().send(phoneSender, smsBody)
        }
    }
}