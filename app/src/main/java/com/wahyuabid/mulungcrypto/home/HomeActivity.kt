package com.wahyuabid.mulungcrypto.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.wahyuabid.mulungcrypto.R
import com.wahyuabid.mulungcrypto.ext.forceOrientation
import com.wahyuabid.mulungcrypto.ext.replaceFragment
import com.wahyuabid.mulungcrypto.home.fragment.airdrop.AirdropFragment
import com.wahyuabid.mulungcrypto.home.fragment.faucet.FaucetFragment
import com.wahyuabid.mulungcrypto.home.fragment.mining.MiningFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    companion object{
        fun getIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }
    }

    private var lastTimeClick: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        forceOrientation()

        tv_header.setText(R.string.nav_faucet)
        bnv_menu.setSelectedItemId(R.id.item_faucet)
        supportFragmentManager.replaceFragment(
            FaucetFragment.TAG,
            R.id.fl_home
        ) {
            FaucetFragment.getInstance()
        }

        bnv_menu?.setOnItemSelectedListener {
            when(it.itemId){
                R.id.item_mining -> {
                    tv_header.setText(R.string.nav_mining)
                    supportFragmentManager.replaceFragment(
                        MiningFragment.TAG,
                        R.id.fl_home
                    ) {
                        MiningFragment.getInstance()
                    }
                    true
                }
                R.id.item_faucet -> {
                    tv_header.setText(R.string.nav_faucet)
                    supportFragmentManager.replaceFragment(
                        FaucetFragment.TAG,
                        R.id.fl_home
                    ) {
                        FaucetFragment.getInstance()
                    }
                    true
                }
                R.id.item_airdrop -> {
                    tv_header.setText(R.string.nav_airdrop)
                    supportFragmentManager.replaceFragment(
                        AirdropFragment.TAG,
                        R.id.fl_home
                    ) {
                        AirdropFragment.getInstance()
                    }
                    true
                }
                else -> false
            }
        }
        bnv_menu.menu.removeItem(R.id.item_mining)
    }

    override fun onBackPressed() {
        if (lastTimeClick) {
            super.onBackPressed()
            return
        }else{
            lastTimeClick = true
            Toast.makeText(this,"Tekan tombol kembali sekali lagi untuk keluar", Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed({ lastTimeClick = false }, 2000)
        }
    }
}