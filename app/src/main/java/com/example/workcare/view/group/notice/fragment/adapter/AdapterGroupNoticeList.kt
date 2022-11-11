package com.example.workcare.view.group.notice.fragment.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workcare.common.datamodel.NoticeDataModel
import com.example.workcare.databinding.ItemNoticeBinding
import com.example.workcare.view.group.notice.ActivityGroupNoticeDetail

class AdapterGroupNoticeList() : RecyclerView.Adapter<AdapterGroupNoticeList.ItemViewHolder>() {

    var itemList = mutableListOf<NoticeDataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(
            ItemNoticeBinding.inflate(
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

    fun setList(list: List<NoticeDataModel>) {
        this.itemList.clear()
        this.itemList.addAll(list)
        notifyDataSetChanged()
    }

    class ItemViewHolder(val binding: ItemNoticeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: NoticeDataModel) {
            try {

                binding.tvTitle.text = data.title

                binding.root.setOnClickListener {
                    val intent = Intent(itemView.context, ActivityGroupNoticeDetail::class.java)
                    intent.putExtra("item", data)
                    itemView.context.startActivity(intent)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}