package com.example.workcare.view.group.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workcare.common.datamodel.GroupDataModel
import com.example.workcare.databinding.ItemGroupBinding
import com.example.workcare.view.group.info.ActivityGroupInfo
import com.example.workcare.view.group.notice.ActivityGroupNotice
import com.example.workcare.view.group.todo.ActivityGroupTodoList

class AdapterGroupList() : RecyclerView.Adapter<AdapterGroupList.ItemViewHolder>() {

    var itemList = mutableListOf<GroupDataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(
            ItemGroupBinding.inflate(
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

    fun setList(list: List<GroupDataModel>) {
        this.itemList.clear()
        this.itemList.addAll(list)
        notifyDataSetChanged()
    }

    class ItemViewHolder(val binding: ItemGroupBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: GroupDataModel) {
            try {

                binding.tvTitle.text = data.groupName

                binding.root.setOnClickListener {

                }

                // 할일
                binding.tvTodo.setOnClickListener {
                    itemView.context.startActivity(Intent(itemView.context, ActivityGroupTodoList::class.java))
                }

                // 전달사항
                binding.tvNotice.setOnClickListener {
                    itemView.context.startActivity(Intent(itemView.context, ActivityGroupNotice::class.java))
                }

                // 그룹정보
                binding.tvInfo.setOnClickListener {
                    itemView.context.startActivity(Intent(itemView.context, ActivityGroupInfo::class.java))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}