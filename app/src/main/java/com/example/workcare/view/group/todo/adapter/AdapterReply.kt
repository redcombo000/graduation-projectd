package com.example.workcare.view.group.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workcare.common.datamodel.ReplyDataModel
import com.example.workcare.databinding.ItemReplyBinding

class AdapterReply() : RecyclerView.Adapter<AdapterReply.ItemViewHolder>() {

    var itemList = mutableListOf<ReplyDataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(
            ItemReplyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(itemList[position])

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setList(list: List<ReplyDataModel>) {
        this.itemList.clear()
        this.itemList.addAll(list)
        notifyDataSetChanged()
    }

    class ItemViewHolder(val binding: ItemReplyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ReplyDataModel) {
            try {

                binding.tvMemberName.text = data.name
                binding.tvContents.text = data.contents

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}