package com.wahyuabid.mulungcrypto.home.fragment.faucet.listitem

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.items.AbstractItem
import com.wahyuabid.mulungcrypto.R
import com.wahyuabid.mulungcrypto.ext.DiffableListItemType
import com.wahyuabid.mulungcrypto.ext.GetDrawableCoin
import com.wahyuabid.mulungcrypto.ext.isFEY
import com.wahyuabid.mulungcrypto.ext.setOnSingleClickListener
import com.wahyuabid.mulungcrypto.home.fragment.faucet.viewmodel.FaucetItemViewModel
import kotlinx.android.synthetic.main.faucet_list_item.view.*

/**
 * Created by Wahyu Abid A on 23/01/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
class FaucetListItem(var viewModel: FaucetItemViewModel,val listener: Listener) :
    AbstractItem<FaucetListItem.ViewHolder>(),
    DiffableListItemType {

    override fun itemIdentifier(): Any = viewModel.hashCode()

    override fun comparableContents(): List<Any> = listOf(
        viewModel.name, viewModel.url, viewModel.image
    )

    override val layoutRes: Int get() = R.layout.faucet_list_item

    override val type: Int get() = hashCode()

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        holder.itemView.apply {
            tv_name.text = viewModel.name

            if(viewModel.type.isFEY()){
                iv_image.setBackgroundColor(ContextCompat.getColor(context,R.color.black100))
            }else{
                iv_image.setBackgroundColor(ContextCompat.getColor(context,R.color.white))
            }

            if(viewModel.name.equals("FaucetPay",ignoreCase = true)){
                iv_image.setImageResource(R.drawable.il_faucetpay)
            }else{
                Glide
                    .with(context)
                    .load(viewModel.image)
                    .error(viewModel.type.GetDrawableCoin())
                    .into(iv_image)
            }
            tv_url.setText(viewModel.url)
            cv_item.setOnSingleClickListener {
                listener.onClick(viewModel.url)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface Listener{
        fun onClick(url:String)
    }
}