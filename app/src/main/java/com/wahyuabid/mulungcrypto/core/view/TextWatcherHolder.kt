package com.wahyuabid.mulungcrypto.core.view

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

/**
 * Created by Wahyu Abid A on 18/12/2021.
 * Email : wahyu.abieddev@gmail.com/wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */

class TextWatcherHolder(
    private val afterTextChangedFunc: ((s: Editable?) -> Unit)? = null,
    private val beforeTextChangedFunc: ((s: CharSequence?, start: Int, count: Int, after: Int) -> Unit)? = null,
    private val onTextChangedFunc: ((s: CharSequence?, start: Int, before: Int, count: Int) -> Unit)? = null
): TextWatcher {

    override fun afterTextChanged(s: Editable?) {
        afterTextChangedFunc?.invoke(s)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        beforeTextChangedFunc?.invoke(s, start, count, after)
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChangedFunc?.invoke(s, start, before, count)
    }
}

fun TextView.afterTextChanged(func: ((s: Editable?) -> Unit)) {
    addTextChangedListener(TextWatcherHolder(afterTextChangedFunc = func))
}
fun TextView.beforeTextChanged(func: (s: CharSequence?, start: Int, count: Int, after: Int) -> Unit) {
    addTextChangedListener(TextWatcherHolder(beforeTextChangedFunc= func))
}
fun TextView.onTextChanged(func: (s: CharSequence?, start: Int, before: Int, count: Int) -> Unit) {
    addTextChangedListener(TextWatcherHolder(onTextChangedFunc= func))
}