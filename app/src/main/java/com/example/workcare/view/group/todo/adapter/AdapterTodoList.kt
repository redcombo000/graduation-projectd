package com.example.workcare.view.group.todo.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workcare.common.datamodel.TodoDataModel
import com.example.workcare.databinding.ItemTodoBinding
import com.example.workcare.view.group.todo.ActivityGroupTodoDetail

class AdapterTodoList() : RecyclerView.Adapter<AdapterTodoList.ItemViewHolder>() {

    var itemList = mutableListOf<TodoDataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(
            ItemTodoBinding.inflate(
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

    fun setList(list: List<TodoDataModel>) {
        this.itemList.clear()
        this.itemList.addAll(list)
        notifyDataSetChanged()
    }

    class ItemViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TodoDataModel) {
            try {

                binding.tvTitle.text = data.title

                binding.root.setOnClickListener {
                    val intent = Intent(itemView.context, ActivityGroupTodoDetail::class.java)
                    intent.putExtra("item", data)
                    itemView.context.startActivity(intent)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}