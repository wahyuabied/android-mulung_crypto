package com.wahyuabid.mulungcrypto.home.fragment.airdrop.listitem

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.items.AbstractItem
import com.wahyuabid.mulungcrypto.R
import com.wahyuabid.mulungcrypto.ext.DiffableListItemType
import com.wahyuabid.mulungcrypto.ext.getEmojiByUnicode
import com.wahyuabid.mulungcrypto.ext.setOnSingleClickListener
import com.wahyuabid.mulungcrypto.home.fragment.airdrop.viewmodel.AirdropItemViewModel
import kotlinx.android.synthetic.main.airdrop_list_item.view.*

/**
 * Created by Wahyu Abid A on 12/03/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
class AirdropListItem(var viewModel: AirdropItemViewModel, var listener: AirdropListItem.Listener) :
    AbstractItem<AirdropListItem.ViewHolder>(),
    DiffableListItemType {

    override fun itemIdentifier(): Any = viewModel.hashCode()

    override fun comparableContents(): List<Any> = listOf(
        viewModel.desc, viewModel.link, viewModel.image, viewModel.reward, viewModel.tokenName
    )

    override val layoutRes: Int get() = R.layout.airdrop_list_item

    override val type: Int get() = hashCode()

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        holder.itemView.apply {
            tv_token_name.setText(viewModel.tokenName)
            tv_reward.setText("${getEmojiByUnicode(	0x1F4B8)} "+viewModel.reward)
            tv_link.setText(viewModel.link)
            tv_desc.setText(viewModel.desc)
            tv_label_reward.text = "Reward:"
            tv_label_link.text = "${getEmojiByUnicode(0x1F680)}${getEmojiByUnicode(0x1F680)}Link${getEmojiByUnicode(0x1F680)}${getEmojiByUnicode(0x1F680)}"
            Glide.with(context).load(viewModel.image)
                .error(R.drawable.il_airdrop)
                .into(iv_image)

            tv_link.setOnSingleClickListener {
                listener.onClickLink(viewModel.link)
            }

            tv_link.setOnLongClickListener {
                listener.onLongClickLink(viewModel.link)
                return@setOnLongClickListener true
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface Listener{
        fun onClickLink(link: String)
        fun onLongClickLink(link: String)
    }
}