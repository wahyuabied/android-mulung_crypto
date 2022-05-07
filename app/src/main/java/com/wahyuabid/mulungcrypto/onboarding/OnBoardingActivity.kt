package com.wahyuabid.mulungcrypto.onboarding

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.wahyuabid.mulungcrypto.home.HomeActivity
import com.wahyuabid.mulungcrypto.R
import com.wahyuabid.mulungcrypto.onboarding.fragment.OnBoardingFragment
import com.wahyuabid.mulungcrypto.sharedpref.UserSharedPref
import kotlinx.android.synthetic.main.activity_on_boarding.*
import org.koin.android.ext.android.inject

class OnBoardingActivity : AppCompatActivity() {
    companion object{
        fun getIntent(context: Context): Intent {
            return Intent(context, OnBoardingActivity::class.java)
        }
    }
    private val onboardViews by lazy {
        listOf(
            OnBoardingFragment.getInstance(
                R.drawable.il_airdrop,
                R.string.title_airdrop,
                R.string.subtitle_airdrop
            ),
            OnBoardingFragment.getInstance(
                R.drawable.il_faucet,
                R.string.title_faucet,
                R.string.subtitle_faucet
            ),
            OnBoardingFragment.getInstance(
                R.drawable.il_mining,
                R.string.title_mining,
                R.string.subtitle_mining
            )
        )
    }
    private val adapter: OnboardingViewPager by lazy {
        OnboardingViewPager(onboardViews, supportFragmentManager)
    }
    private val indicators by lazy {
        arrayOf(iv_indicator_1, iv_indicator_2, iv_indicator_3)
    }
    private val userPref: UserSharedPref by inject ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        vp_onboarding?.adapter = adapter
        sliderViewPager()

        btn_start.setOnClickListener {
            if(vp_onboarding.currentItem == 2){
                userPref.setFirstTime(false)
                startActivity(HomeActivity.getIntent(this))
                finish()
            }else{
                vp_onboarding.post(Runnable { vp_onboarding.setCurrentItem((vp_onboarding.getCurrentItem() + 1) % onboardViews.size) })
            }
        }

        tv_skip.setOnClickListener{
            userPref.setFirstTime(false)
            startActivity(HomeActivity.getIntent(this))
        }
    }

    fun sliderViewPager(){
        vp_onboarding?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                indicators.forEachIndexed { index, _ ->
                    indicators[index].setBackgroundResource(if (index == position) R.drawable.shape_primary_oval else R.drawable.shape_gray_oval)
                }
            }

            override fun onPageSelected(position: Int) {
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

class OnboardingViewPager(val fragmentViews: List<Fragment>, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragmentViews[position]
    }

    override fun getCount(): Int {
        return fragmentViews.size
    }
}