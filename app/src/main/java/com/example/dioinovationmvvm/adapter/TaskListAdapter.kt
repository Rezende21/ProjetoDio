package com.example.dioinovationmvvm.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dioinovationmvvm.R
import com.example.dioinovationmvvm.databinding.ListitemBinding
import com.example.dioinovationmvvm.model.Task

class TaskListAdapter(
    private val onTaskClick : (Task) -> Unit
) : ListAdapter<Task, TaskListAdapter.TaskViewHolder>(DiffCallback()) {

    var listenerDelete : (Task) -> Unit = {}

    inner class  TaskViewHolder(private val binding: ListitemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind (item : Task) {
            binding.txtTitleItem.text = item.title
            binding.txtDataItem.text = "${item.date} ${item.hour}"
            binding.moreItem.setOnClickListener {
                showPopup(item)
            }
        }

        private fun showPopup(item: Task) {
            val ivMore = binding.moreItem
            val popupMenu = PopupMenu(ivMore.context, ivMore)
            popupMenu.menuInflater.inflate(R.menu.popupmenu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_delete -> listenerDelete(item)
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListitemBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.itemView.setOnClickListener {
            onTaskClick(task)
        }
        holder.bind(task)
    }

    class DiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem == newItem
    }
}

