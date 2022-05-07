package com.wahyuabid.mulungcrypto.home.fragment.airdrop.viewmodel

import com.wahyuabid.mulungcrypto.R
import com.wahyuabid.mulungcrypto.home.fragment.faucet.viewmodel.FaucetItemViewModelType

/**
 * Created by Wahyu Abid A on 12/03/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */

sealed class AirdropItemViewModelType

data class AirdropItemViewModel(
    val reward: String,
    val tokenName: String,
    val desc: String,
    val link: String,
    val image: String,
): AirdropItemViewModelType()

data class EmptyAirdropItemViewModel(
    val image: Int = R.drawable.il_airdrop,
    val title: Int = R.string.empty_airdrop_title,
    val desc: Int = R.string.empty_airdrop_desc
): AirdropItemViewModelType()