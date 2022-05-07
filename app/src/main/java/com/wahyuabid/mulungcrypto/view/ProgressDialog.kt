package com.wahyuabid.mulungcrypto.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.core.content.ContextCompat
import com.wahyuabid.mulungcrypto.R
import kotlinx.android.synthetic.main.layout_dialog_progress.*

/**
 * Created by Wahyu Abid A on 06/02/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
class ProgressDialog(
    context: Context,
    var loadingTitle: String = "",
    var textColor: Int = R.color.colorPrimary
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.layout_dialog_progress)

        if (this.loadingTitle != "") {
            tv_loading.text = this.loadingTitle
            tv_loading.visibility = View.VISIBLE
            tv_loading.setTextColor(
                ContextCompat.getColor(
                    context,
                    textColor
                )
            )
        } else {
            tv_loading.visibility = View.GONE
        }

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCanceledOnTouchOutside(false)
        setCancelable(false)
    }

    fun showLoading(isShow: Boolean) {
        if (isShow) {
            show()
        } else {
            dismiss()
        }
    }
}