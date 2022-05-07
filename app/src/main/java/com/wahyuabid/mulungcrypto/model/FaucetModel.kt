package com.wahyuabid.mulungcrypto.model

/**
 * Created by Wahyu Abid A on 22/01/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
class FaucetModel(
    val id: Int = 0,
    val name: String = "",
    val url: String,
    val type: String,
    val alarm: Double ?= null
)