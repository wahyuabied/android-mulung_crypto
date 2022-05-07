package com.wahyuabid.mulungcrypto.core.di

import android.content.Context
import com.google.gson.Gson
import com.wahyuabid.mulungcrypto.BuildConfig
import com.wahyuabid.mulungcrypto.core.firebaseconfig.Config
import com.wahyuabid.mulungcrypto.core.firebaseconfig.FCMManager
import com.wahyuabid.mulungcrypto.core.firebaseconfig.FCMManagerImpl
import com.wahyuabid.mulungcrypto.core.firebaseconfig.FirebaseConfig
import com.wahyuabid.mulungcrypto.home.homeModule
import com.wahyuabid.mulungcrypto.sharedpref.UserSharedPref
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created by Wahyu Abid A on 19/12/21
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */

val netModule = module {
    factory { Gson() }
}

val preferenceModule = module {
    factory {
        androidApplication().applicationContext.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }
    factory { UserSharedPref(get()) }
}

val configModule = module {
    single<Config> { FirebaseConfig() }
    single<FCMManager> { FCMManagerImpl().apply { initialize() } }
}

val appModules = listOf(
    configModule,
    preferenceModule,
    netModule,
    homeModule
)