package com.wahyuabid.mulungcrypto.core.firebaseconfig

/**
 * Created by Wahyu Abid A on 29/12/21
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
interface Config {
    fun fetch(listener: FetchListener? = null)
    fun getString(key: String, defaultValue: String = ""): String
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean
    fun getDouble(key: String, defaultValue: Double = 0.0): Double
    fun getLong(key: String, defaultValue: Long = 0L): Long
}

interface FetchListener {
    fun onSuccess(config: Config)
    fun onError(err: Throwable)
}