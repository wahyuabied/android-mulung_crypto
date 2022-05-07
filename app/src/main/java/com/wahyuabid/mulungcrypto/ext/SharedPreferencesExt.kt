package com.wahyuabid.mulungcrypto.ext

import android.content.SharedPreferences

/**
 * Created by Wahyu Abid A on 19/12/21
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
@Suppress("UNCHECKED_CAST")
fun SharedPreferences.update(vararg values: Pair<String, Any>) {
    val editor = this.edit()
    values.forEach {
        when (it.second) {
            is Int -> editor.putInt(it.first, it.second as Int)
            is Boolean -> editor.putBoolean(it.first, it.second as Boolean)
            is Float -> editor.putFloat(it.first, it.second as Float)
            is Long -> editor.putLong(it.first, it.second as Long)
            is String -> editor.putString(it.first, it.second as String)
            is MutableSet<*> -> editor.putStringSet(it.first, it.second as Set<String>)
        }
    }
    editor.apply()
}

fun SharedPreferences.removes(vararg keys: String) {
    val editor = this.edit()
    keys.forEach {
        editor.remove(it)
    }
    editor.apply()
}