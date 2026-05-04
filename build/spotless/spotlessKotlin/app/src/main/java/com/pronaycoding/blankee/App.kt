package com.pronaycoding.blankee

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.pronaycoding.blankee.core.service.billing.PlayBillingManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseCrashlytics
            .getInstance()
            .setCrashlyticsCollectionEnabled(BuildConfig.BUILD_TYPE == "release")

        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger(Level.DEBUG)
            }
            androidContext(this@App)
            modules(KoinModule.allModules)
        }
        GlobalContext.get().get<PlayBillingManager>().start()
    }
}
