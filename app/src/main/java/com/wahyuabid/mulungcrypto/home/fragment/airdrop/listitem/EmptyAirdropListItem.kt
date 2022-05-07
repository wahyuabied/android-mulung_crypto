package com.wahyuabid.mulungcrypto.home.fragment.airdrop.listitem

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.AbstractItem
import com.wahyuabid.mulungcrypto.R
import com.wahyuabid.mulungcrypto.ext.DiffableListItemType
import com.wahyuabid.mulungcrypto.ext.invisible
import com.wahyuabid.mulungcrypto.home.fragment.airdrop.viewmodel.EmptyAirdropItemViewModel
import kotlinx.android.synthetic.main.view_empty.view.*

/**
 * Created by Wahyu Abid A on 12/03/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
class EmptyAirdropListItem(var viewModel: EmptyAirdropItemViewModel) :
    AbstractItem<EmptyAirdropListItem.ViewHolder>(),
    DiffableListItemType {

    override fun itemIdentifier(): Any = viewModel.image

    override fun comparableContents(): List<Any> = listOf(
        viewModel.image, viewModel.title, viewModel.desc
    )

    override val layoutRes: Int get() = R.layout.view_empty

    override val type: Int get() = hashCode()

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        if (viewModel.image == 0)
            holder.itemView.iv_empty.invisible()
        else
            holder.itemView.iv_empty.setImageResource(viewModel.image)
        holder.itemView.tv_title.setText(viewModel.title)
        if (viewModel.desc == 0) {
            holder.itemView.tv_description.text = ""
        } else {
            holder.itemView.tv_description.setText(viewModel.desc)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}