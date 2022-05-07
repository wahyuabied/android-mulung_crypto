package com.wahyuabid.mulungcrypto.sharedpref

import android.content.SharedPreferences
import com.google.gson.Gson
import com.wahyuabid.mulungcrypto.ext.removes
import com.wahyuabid.mulungcrypto.ext.update

/**
 * Created by Wahyu Abid A on 19/12/21
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */

class UserSharedPref(private val pref: SharedPreferences) {
    companion object {
        const val SESSION = "session"
        const val USER_INFO = "user_info"
        const val IS_FIRST_TIME = "is_first_time"
    }

    fun isFirstTime():Boolean{
        return pref.getBoolean(IS_FIRST_TIME,true)
    }

    fun setFirstTime(value: Boolean){
        pref.update(IS_FIRST_TIME to false)
    }

    fun clearSession(){
        pref.removes(IS_FIRST_TIME)
    }
}