package com.wahyuabid.mulungcrypto.home.fragment.airdrop.viewmodel

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wahyuabid.mulungcrypto.core.firebaseconfig.Config
import com.wahyuabid.mulungcrypto.core.firebaseconfig.KEY
import com.wahyuabid.mulungcrypto.core.viewmodel.BaseViewModel
import com.wahyuabid.mulungcrypto.model.AirdropModel
import com.wahyuabid.mulungcrypto.model.FaucetModel
import java.lang.Exception

/**
 * Created by Wahyu Abid A on 12/03/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
class AirdropViewModel(
    val firebaseConfig: Config
) : BaseViewModel() {

    val shouldShowAirdrop: MutableLiveData<MutableList<AirdropItemViewModelType>> =
        MutableLiveData()

    private var listAirdrop = mutableListOf<AirdropModel>()

    fun onViewLoaded() {
        listAirdrop = mutableListOf()
        try {
            Gson().fromJson<List<AirdropModel>>(
                firebaseConfig.getString(KEY.AIRDROP).trim(),
                object : TypeToken<List<AirdropModel>>() {}.type
            ).map {
                listAirdrop.add(it)
            }
        } catch (e: Exception) {
            firebaseConfig.fetch()
        }
        populateItemViewModel()
    }

    fun populateItemViewModel() {
        val items: MutableList<AirdropItemViewModelType> = mutableListOf()
        if (listAirdrop.isEmpty()) {
            items.add(EmptyAirdropItemViewModel())
        } else {
            listAirdrop.forEach {
                items.add(
                    AirdropItemViewModel(
                        it.reward,
                        it.tokenName,
                        it.desc,
                        it.link,
                        it.image
                    )
                )
            }
        }
        shouldShowAirdrop.value = items
    }
}
