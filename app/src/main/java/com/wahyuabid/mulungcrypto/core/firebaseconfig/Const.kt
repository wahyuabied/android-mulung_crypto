package com.wahyuabid.mulungcrypto.core.firebaseconfig

/**
 * Created by Wahyu Abid A on 29/12/21
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */

val DEFAULT_INITIAL_VALUE: HashMap<String, Any> = hashMapOf(
    KEY.TEST to true,
    KEY.FAUCET to "[]",
    KEY.AIRDROP to "[]"
)

class KEY {
    companion object {
        const val TEST = "test"
        const val FAUCET = "faucet"
        const val AIRDROP = "airdrop"
    }
}
