package com.wahyuabid.mulungcrypto

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.wahyuabid.mulungcrypto.home.HomeActivity
import com.wahyuabid.mulungcrypto.onboarding.OnBoardingActivity
import com.wahyuabid.mulungcrypto.sharedpref.UserSharedPref
import org.koin.android.ext.android.inject

class SplashScreenActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, SplashScreenActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }
    }

    private val userPref: UserSharedPref by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed({
            if(userPref.isFirstTime()){
                startActivity(OnBoardingActivity.getIntent(this))
            }else{
                startActivity(HomeActivity.getIntent(this))
            }
            finish()
        }, 2000)
    }
}