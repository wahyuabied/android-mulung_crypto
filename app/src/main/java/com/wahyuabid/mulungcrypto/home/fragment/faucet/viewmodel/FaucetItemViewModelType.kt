package com.wahyuabid.mulungcrypto.home.fragment.faucet.viewmodel

/**
 * Created by Wahyu Abid A on 22/01/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
sealed class FaucetItemViewModelType

data class FaucetItemViewModel(
    val name: String,
    val url: String,
    val image: String,
    val type: String,
):FaucetItemViewModelType()