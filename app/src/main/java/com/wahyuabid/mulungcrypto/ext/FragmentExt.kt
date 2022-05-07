package com.wahyuabid.mulungcrypto.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.wahyuabid.mulungcrypto.R

/**
 * Created by Wahyu Abid A on 28/12/21
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
inline fun FragmentManager.inflateFragment(tag: String, layout: Int, func: () -> Fragment) {
    if (findFragmentByTag(tag) == null) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction
            .add(layout, func(), tag)
            .commit()
    }
}

inline fun FragmentManager.replaceFragment(tag: String, layout: Int, func: () -> Fragment) {
    if (findFragmentByTag(tag) == null) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction
            .replace(layout, func(), tag)
            .commit()
    }
}

inline fun FragmentManager.replaceFragmentWithoutBackstack(
    tag: String,
    layout: Int,
    func: () -> Fragment,
    isFirstFragment: Boolean = false
) {
    if (findFragmentByTag(tag) == null) {
        if (isFirstFragment) {
            popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        beginTransaction().apply {
            replace(layout, func(), tag)
            if (!isFirstFragment) {
                addToBackStack(null)
            }
        }.commit()
    }
}

inline fun FragmentManager.replaceFragmentWithBackstack(
    tag: String,
    layout: Int,
    func: () -> Fragment,
    isFirstFragment: Boolean = false
) {
    if (isFirstFragment) {
        popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
    beginTransaction().apply {
        replace(layout, func(), tag)
        if (!isFirstFragment) {
            addToBackStack(null)
        }
    }.commit()
}

fun Fragment.isTabletMode(): Boolean {
    return resources.getBoolean(R.bool.is_tablet)
}

fun Fragment.isFragmentGone(): Boolean {
    return this.activity?.isFinishing == true || this.activity?.isDestroyed == true || this.isRemoving || !this.isVisible
}