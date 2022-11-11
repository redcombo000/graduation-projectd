package com.example.workcare.view.group.info.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workcare.common.datamodel.UserDataModel
import com.example.workcare.databinding.ItemGroupMemberBinding
import com.example.workcare.view.group.info.ActivityMemberInfo

class AdapterGroupMemberList() : RecyclerView.Adapter<AdapterGroupMemberList.ItemViewHolder>() {

    var itemList = mutableListOf<UserDataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(
            ItemGroupMemberBinding.inflate(
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

    fun setList(list: List<UserDataModel>) {
        this.itemList.clear()
        this.itemList.addAll(list)
        notifyDataSetChanged()
    }

    class ItemViewHolder(val binding: ItemGroupMemberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: UserDataModel) {
            try {

                binding.imgKing.visibility = if(adapterPosition == 0) View.VISIBLE else View.GONE
                binding.tvName.text = data.name
                binding.tvTel.text = data.phoneNumber

                binding.root.setOnClickListener {
                    // 회원 상세
                    val intent = Intent(itemView.context, ActivityMemberInfo::class.java)
                    intent.putExtra("user", data)
                    itemView.context.startActivity(intent)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}