package com.wahyuabid.mulungcrypto.home.fragment.airdrop

import android.content.Intent
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
import com.wahyuabid.mulungcrypto.home.fragment.airdrop.listitem.AirdropListItem
import com.wahyuabid.mulungcrypto.home.fragment.airdrop.listitem.EmptyAirdropListItem
import com.wahyuabid.mulungcrypto.home.fragment.airdrop.viewmodel.AirdropItemViewModel
import com.wahyuabid.mulungcrypto.home.fragment.airdrop.viewmodel.AirdropItemViewModelType
import com.wahyuabid.mulungcrypto.home.fragment.airdrop.viewmodel.AirdropViewModel
import com.wahyuabid.mulungcrypto.home.fragment.airdrop.viewmodel.EmptyAirdropItemViewModel
import kotlinx.android.synthetic.main.fragment_airdrop.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AirdropFragment : Fragment() {

    companion object {
        const val TAG = "AirdropFragment"
        fun getInstance(): AirdropFragment {
            return AirdropFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_airdrop, container, false)
    }

    private val viewModel by viewModel<AirdropViewModel>()
    private val fastAdapter: FastItemAdapter<UnspecifiedTypeItem> = FastItemAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_airdrop.adapter = fastAdapter
        rv_airdrop.itemAnimator = null

        with(viewModel){
            shouldShowAirdrop.observe(this@AirdropFragment.viewLifecycleOwner, Observer {
                populate(it)
            })
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.onViewLoaded()
    }

    private fun populate(itemViewModel: List<AirdropItemViewModelType>){
        val items: List<UnspecifiedTypeItem> = itemViewModel.map {
            val item: UnspecifiedTypeItem = when(it){
                is AirdropItemViewModel -> createList(it)
                is EmptyAirdropItemViewModel -> createEmpty(it)
            }
            return@map item
        }
        fastAdapter.performUpdates(items)
    }

    private fun createList(itemViewModel: AirdropItemViewModel): AirdropListItem {
        return AirdropListItem(itemViewModel,object : AirdropListItem.Listener{
            override fun onClickLink(link: String) {
                activity?.startActivity(BrowserActivity.getIntent(requireContext(),link))
            }

            override fun onLongClickLink(link: String) {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type="text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, link)
                startActivity(Intent.createChooser(shareIntent,"Share with:"))
            }
        })
    }

    private fun createEmpty(itemViewModel: EmptyAirdropItemViewModel): EmptyAirdropListItem{
        return EmptyAirdropListItem(itemViewModel)
    }
}
