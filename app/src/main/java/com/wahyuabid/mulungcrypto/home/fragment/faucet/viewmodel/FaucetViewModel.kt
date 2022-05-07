package com.wahyuabid.mulungcrypto.home.fragment.faucet.viewmodel

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wahyuabid.mulungcrypto.R
import com.wahyuabid.mulungcrypto.core.firebaseconfig.Config
import com.wahyuabid.mulungcrypto.core.firebaseconfig.KEY
import com.wahyuabid.mulungcrypto.core.viewmodel.BaseViewModel
import com.wahyuabid.mulungcrypto.model.FaucetModel
import com.wahyuabid.mulungcrypto.model.FaucetType

/**
 * Created by Wahyu Abid A on 22/01/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
class FaucetViewModel(
    val firebaseConfig: Config
) : BaseViewModel() {

    val shouldShowData: MutableLiveData<MutableList<FaucetItemViewModelType>> = MutableLiveData()
    var listFaucet = mutableListOf<FaucetModel>()

    fun onViewLoaded() {
        listFaucet = mutableListOf(
            FaucetModel(name = "FaucetPay", url = "https://faucetpay.io", type = FaucetType.NONE)
        )
        try {
            Gson().fromJson<List<FaucetModel>>(
                firebaseConfig.getString(KEY.FAUCET).trim(),
                object : TypeToken<List<FaucetModel>>() {}.type
            ).map {
                listFaucet.add(it)
            }
        } catch (e: Exception) {
            firebaseConfig.fetch()
        }

        populateItemViewModel()
    }

    fun populateItemViewModel() {
        val items = mutableListOf<FaucetItemViewModelType>()
        listFaucet.forEach {
            items.add(
                FaucetItemViewModel(
                    it.name,
                    it.url,
                    "https://logo.clearbit.com/${it.url}",
                    it.type
                )
            )
        }
        shouldShowData.value = items
    }
}
