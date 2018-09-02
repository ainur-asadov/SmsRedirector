package ru.ohrana_neva.smsredirector

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {

    companion object {
        lateinit var app: App
    }

    override fun onCreate() {
        super.onCreate()

        app = this

        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .name("Images.realm")
                /*.schemaVersion(1)
                .migration(MyMigration())*/
                .build()
        Realm.setDefaultConfiguration(config)
    }
}