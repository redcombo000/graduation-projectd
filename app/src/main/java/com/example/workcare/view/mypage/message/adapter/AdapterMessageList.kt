package com.example.workcare.view.mypage.message.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.workcare.common.datamodel.MessageDataModel
import com.example.workcare.databinding.ItemMessageBinding

class AdapterMessageList() : RecyclerView.Adapter<AdapterMessageList.ItemViewHolder>() {

    var itemList = mutableListOf<MessageDataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(
            ItemMessageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(itemList[position])

        holder.binding.rlReply.setOnClickListener {
            Toast.makeText(holder.binding.root.context, "답장", Toast.LENGTH_SHORT).show()
        }
        holder.binding.rlDelete.setOnClickListener {
            Toast.makeText(holder.binding.root.context, "삭제 완료", Toast.LENGTH_SHORT).show()
            remove(position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setList(list: List<MessageDataModel>) {
        this.itemList.clear()
        this.itemList.addAll(list)
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemList.size)
    }

    class ItemViewHolder(val binding: ItemMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: MessageDataModel) {
            try {

                binding.tvTitle.text = data.title
                binding.tvContents.text = data.contents

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}