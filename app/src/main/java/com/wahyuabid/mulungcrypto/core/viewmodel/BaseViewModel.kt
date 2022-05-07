package com.wahyuabid.mulungcrypto.core.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Wahyu Abid A on 19/12/21
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
open class BaseViewModel: ViewModel() {

    protected val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    open val shouldShowError: MutableLiveData<Any> = MutableLiveData()
    open val shouldShowLoading: MutableLiveData<Boolean> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    open fun handleError(e: Throwable) {
        shouldShowError.value = e
    }

}