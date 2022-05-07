package com.wahyuabid.mulungcrypto.ext

import android.app.Activity
import android.content.pm.ActivityInfo
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.google.android.material.snackbar.Snackbar
import com.wahyuabid.mulungcrypto.R

/**
 * Created by Wahyu Abid A on 24/01/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */

fun Activity.isTabletMode(): Boolean {
    return resources.getBoolean(R.bool.is_tablet)
}

fun AppCompatActivity.forceOrientation() {
    requestedOrientation = if (this.isTabletMode()) {
        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    } else {
        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}

fun Activity.showSnackbar(
    message: String = getString(R.string.label_connection_problem),
    rootView: View = findViewById(android.R.id.content),
    duration: Int = Snackbar.LENGTH_SHORT,
    drawables: Int = R.drawable.shape_red_round
) {
    Snackbar.make(
        rootView,
        HtmlCompat.fromHtml(message, HtmlCompat.FROM_HTML_MODE_LEGACY),
        duration
    ).apply {
        view.setBackgroundResource(drawables)
        setActionTextColor(ContextCompat.getColor(context, R.color.white))
    }.show()
}