package com.wahyuabid.mulungcrypto.ext

import com.wahyuabid.mulungcrypto.R
import com.wahyuabid.mulungcrypto.model.FaucetType

/**
 * Created by Wahyu Abid A on 01/03/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
fun String.GetDrawableCoin(): Int {
    return when{
        this.isBTC() -> R.drawable.coin_btc
        this.isETH() -> R.drawable.coin_eth
        this.isDOGE() -> R.drawable.coin_doge
        this.isLTC() -> R.drawable.coin_ltc
        this.isBCH() -> R.drawable.coin_bch
        this.isDASH() -> R.drawable.coin_dash
        this.isDGB() -> R.drawable.coin_dgb
        this.isTRX() -> R.drawable.coin_trx
        this.isTETHER() -> R.drawable.coin_tether
        this.isFEY() -> R.drawable.coin_feyora
        this.isZEC() -> R.drawable.coin_zcash
        this.isBNB() -> R.drawable.coin_bnb
        this.isSOL() -> R.drawable.coin_sol
        this.isNONE() -> R.drawable.il_splash_logo
        else -> R.drawable.il_splash_logo
    }
}

fun String.isBTC(): Boolean {
    return this.equals(FaucetType.BTC, ignoreCase = true)
}

fun String.isETH(): Boolean {
    return this.equals(FaucetType.ETH, ignoreCase = true)
}

fun String.isDOGE(): Boolean {
    return this.equals(FaucetType.DOGE, ignoreCase = true)
}

fun String.isLTC(): Boolean {
    return this.equals(FaucetType.LTC, ignoreCase = true)
}

fun String.isBCH(): Boolean {
    return this.equals(FaucetType.BCH, ignoreCase = true)
}

fun String.isDASH(): Boolean {
    return this.equals(FaucetType.DASH, ignoreCase = true)
}

fun String.isDGB(): Boolean {
    return this.equals(FaucetType.DGB, ignoreCase = true)
}

fun String.isTRX(): Boolean {
    return this.equals(FaucetType.TRX, ignoreCase = true)
}

fun String.isTETHER(): Boolean {
    return this.equals(FaucetType.TETHER, ignoreCase = true)
}

fun String.isFEY(): Boolean {
    return this.equals(FaucetType.FEY, ignoreCase = true)
}

fun String.isZEC(): Boolean {
    return this.equals(FaucetType.ZEC, ignoreCase = true)
}

fun String.isBNB(): Boolean {
    return this.equals(FaucetType.BNB, ignoreCase = true)
}

fun String.isSOL(): Boolean {
    return this.equals(FaucetType.SOL, ignoreCase = true)
}

fun String.isNONE(): Boolean {
    return this.equals(FaucetType.NONE, ignoreCase = true)
}