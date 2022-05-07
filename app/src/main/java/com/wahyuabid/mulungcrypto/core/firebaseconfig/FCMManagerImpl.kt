package com.wahyuabid.mulungcrypto.core.firebaseconfig

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging


/**
 * Created by Wahyu Abid A on 29/12/21
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
class FCMManagerImpl : FCMManager {
    private var token: String? = null

    override fun initialize() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (it.isSuccessful){
                return@addOnCompleteListener
            }
            token = it.result
        }
    }

    override fun deleteInstanceId() {
        FirebaseMessaging.getInstance().deleteToken()
    }

    override fun getToken(): String? = token
}