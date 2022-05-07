package com.wahyuabid.mulungcrypto.onboarding.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wahyuabid.mulungcrypto.R
import kotlinx.android.synthetic.main.fragment_on_boarding.*

class OnBoardingFragment : Fragment() {

    companion object {
        const val TAG = "OnboardingFragment"
        private const val BACKGROUND = "BACKGROUND"
        private const val TITLE = "TITLE"
        private const val SUBTITLE = "SUBTITLE"
        fun getInstance(
            imageBackground: Int,
            title: Int,
            subtitle: Int
        ): OnBoardingFragment {
            return OnBoardingFragment().apply {
                arguments = Bundle().apply {
                    putInt(BACKGROUND, imageBackground)
                    putInt(TITLE, title)
                    putInt(SUBTITLE, subtitle)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_on_boarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            iv_onboarding.setImageResource(it.getInt(BACKGROUND))
            tv_title.setText(it.getInt(TITLE))
            tv_subtitle.setText(it.getInt(SUBTITLE))
        }
    }
}