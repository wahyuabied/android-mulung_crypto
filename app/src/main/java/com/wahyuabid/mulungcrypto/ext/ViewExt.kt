package com.wahyuabid.mulungcrypto.ext

/**
 * Created by Wahyu Abid A on 24/01/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.SystemClock
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

fun View.setOnSingleClickListener(onSingleClick: (View) -> Unit) {
    val safeClickListener = SingleClickListener {
        onSingleClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun View.hideKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun BottomSheetDialogFragment.showFullBottomSheet(view: View, enablePeekHeight:Boolean = true) {
    view.viewTreeObserver.addOnGlobalLayoutListener(object :
        ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            view.viewTreeObserver.removeOnGlobalLayoutListener(this)
            val dialog = dialog as BottomSheetDialog
            val bottomSheet =
                dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                if(enablePeekHeight)
                    behavior.peekHeight =
                        0 // Remove this line to hide a dark background if you manually hide the dialog.
            }
        }
    })
}

fun TextView.setColorRes(resInt: Int) {
    this.setTextColor(ContextCompat.getColor(this.context, resInt))
}

fun TextView.setBgColorRes(resInt: Int) {
    this.setBackgroundColor(ContextCompat.getColor(this.context, resInt))
}

fun TextView.setDrawableColor(color: Int) {
    for (drawable in compoundDrawables) {
        if (drawable != null) {
            drawable.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_ATOP)
        }
    }
}

fun View.loadingAnimation() {
    val fadeAnim = ObjectAnimator.ofFloat(this, "alpha", 1f, 0.15f)
    fadeAnim.duration = 500
    fadeAnim.repeatCount = ObjectAnimator.INFINITE
    fadeAnim.repeatMode = ObjectAnimator.REVERSE
    fadeAnim.start()
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun getEmojiByUnicode(unicode: Int): String {
    return String(Character.toChars(unicode))
}

class SingleClickListener(private var interval: Int = 800, private val onSingleClick: (View) -> Unit) : View.OnClickListener {

    private var lastTimeClicked: Long = 0

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < interval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSingleClick(v)
    }
}