package com.wahyuabid.mulungcrypto.model

import java.util.*

/**
 * Created by Wahyu Abid A on 06/03/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
class AirdropModel(
    val id: Int = 0,
    val tokenName: String = "",
    val reward: String = "",
    val desc: String = "",
    val link: String = "",
    val image: String = "",
    val alarm: Double ?= null
)