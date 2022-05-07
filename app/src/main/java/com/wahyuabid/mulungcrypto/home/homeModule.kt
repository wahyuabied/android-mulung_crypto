package com.wahyuabid.mulungcrypto.home

import com.wahyuabid.mulungcrypto.home.fragment.airdrop.viewmodel.AirdropViewModel
import com.wahyuabid.mulungcrypto.home.fragment.faucet.viewmodel.FaucetViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Wahyu Abid A on 23/01/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
val homeModule = module {
    viewModel { FaucetViewModel(get()) }
    viewModel { AirdropViewModel(get()) }
}