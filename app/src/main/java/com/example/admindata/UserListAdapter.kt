package com.example.admindata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.modelresp.UsersListResp.Result
import com.example.admindata.databinding.UserslistitemBinding

class UserListAdapter(private var userList: List<Result>, private val itemClickListener: (String) -> Unit) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: UserslistitemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, user: Result) {
            binding.UserNames.text = "${position + 1}. ${user.username}"
            itemView.setOnClickListener { itemClickListener(user.username) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserslistitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position , userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateData(newList: List<Result>) {
        userList = newList
        notifyDataSetChanged()
    }
}
