package com.wahyuabid.mulungcrypto.core
import androidx.multidex.MultiDexApplication
import com.wahyuabid.mulungcrypto.core.di.appModules
import com.wahyuabid.mulungcrypto.core.firebaseconfig.Config
import com.wahyuabid.mulungcrypto.core.firebaseconfig.FirebaseConfig
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

/**
 * Created by Wahyu Abid A on 18/12/2021.
 * Email : wahyu.abieddev@gmail.com/wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */

class MainApplication: MultiDexApplication() {

    val firebaseConfig: Config by inject ()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(appModules)
            logger(EmptyLogger())
        }

        firebaseConfig.fetch()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}