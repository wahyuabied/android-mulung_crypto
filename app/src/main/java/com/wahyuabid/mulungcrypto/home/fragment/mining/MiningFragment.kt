package com.wahyuabid.mulungcrypto.home.fragment.mining

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wahyuabid.mulungcrypto.R

class MiningFragment : Fragment() {

    companion object {
        const val TAG = "MiningFragment"
        fun getInstance(): MiningFragment {
            return MiningFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mining, container, false)
    }
}
