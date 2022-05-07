package com.wahyuabid.mulungcrypto.core.firebaseconfig

/**
 * Created by Wahyu Abid A on 29/12/21
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
interface FCMManager {
    fun initialize()
    fun deleteInstanceId()
    fun getToken(): String?
}