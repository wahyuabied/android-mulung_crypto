package com.wahyuabid.mulungcrypto.home.fragment.faucet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.wahyuabid.mulungcrypto.R
import com.wahyuabid.mulungcrypto.browser.BrowserActivity
import com.wahyuabid.mulungcrypto.ext.UnspecifiedTypeItem
import com.wahyuabid.mulungcrypto.ext.performUpdates
import com.wahyuabid.mulungcrypto.ext.showSnackbar
import com.wahyuabid.mulungcrypto.home.fragment.faucet.listitem.FaucetListItem
import com.wahyuabid.mulungcrypto.home.fragment.faucet.viewmodel.FaucetItemViewModel
import com.wahyuabid.mulungcrypto.home.fragment.faucet.viewmodel.FaucetItemViewModelType
import com.wahyuabid.mulungcrypto.home.fragment.faucet.viewmodel.FaucetViewModel
import kotlinx.android.synthetic.main.fragment_faucet.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class FaucetFragment : Fragment() {
    companion object {
        const val TAG = "FaucetFragment"
        fun getInstance(): FaucetFragment {
            return FaucetFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_faucet, container, false)
    }

    private val viewModel: FaucetViewModel by viewModel ()
    private val fastAdapter: FastItemAdapter<UnspecifiedTypeItem> = FastItemAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_faucet.itemAnimator = null
        rv_faucet.adapter = fastAdapter

        with(viewModel){
            shouldShowData.observe(this@FaucetFragment.viewLifecycleOwner, Observer(::populate))
        }
    }

    private fun populate(itemViewModel: List<FaucetItemViewModelType>){
        val items: List<UnspecifiedTypeItem> = itemViewModel.map {
            val item: UnspecifiedTypeItem = when(it){
                is FaucetItemViewModel -> createList(it)
            }
            return@map item
        }
        fastAdapter.performUpdates(items)
    }

    private fun createList(itemViewModel: FaucetItemViewModel): FaucetListItem{
        return FaucetListItem(itemViewModel,object :FaucetListItem.Listener{
            override fun onClick(url: String) {
                activity?.startActivity(BrowserActivity.getIntent(requireContext(),url))
            }
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.onViewLoaded()
    }
}
