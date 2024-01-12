package com.android.applemarket

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.res.Resources
import android.icu.text.DecimalFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.applemarket.databinding.MainListItemBinding

class ListAdapter (val Items: MutableList<Product>) : RecyclerView.Adapter<ListAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
        fun longClick(view : View, position: Int)
    }

    var itemClick : ItemClick? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = MainListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {  //클릭이벤트추가부분
            itemClick?.onClick(it, position)
        }

        holder.itemView.setOnLongClickListener {
            Log.d("test","position: ${holder.adapterPosition}")
            itemClick?.longClick(it,holder.adapterPosition) //구글에서 권장하는 방식 adapterPosition
            true
        }
        holder.pname.text = Items[position].pname
        holder.address.text = Items[position].address
        //val imgname = Items[position].imgsrc
        //val resId = resources.getIdentifier(imgname,"drawble","com.android.applemarket")
        holder.imgsrc.setImageResource(Items[position].imgsrc)

        val deciamlFormat = DecimalFormat("#,###")
        val cPrice = deciamlFormat.format(Items[position].price)
        holder.price.text = "${cPrice} 원"
        holder.chat.text = Items[position].chat.toString()
        holder.like.text = Items[position].like.toString()
        if(Items[position].isLike)
            holder.likeImg.setImageResource(R.drawable.heartredfill)
        else
            holder.likeImg.setImageResource(R.drawable.heart)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return Items.size
    }

    inner class Holder(val binding: MainListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        var imgsrc = binding.ivItemPic2
        var pname = binding.tvItemTitle
        var price = binding.tvPrice
        var address = binding.tvAddress
        var like =binding.tvLikeCount
        var chat =binding.tvCommentCount
        var likeImg = binding.ivLike
    }

}

