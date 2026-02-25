package com.githbuz.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azer.presentation.databinding.ItemUserBinding
import com.bumptech.glide.Glide
import com.githbuz.domain.model.User

class UserAdapter(
    private val onItemClick: (User) -> Unit,
    private val onFavoriteClick: (User) -> Unit
) : ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(getItem(adapterPosition))
            }
            binding.tbFavorite.setOnClickListener {
                onFavoriteClick(getItem(adapterPosition))
            }
        }

        fun bind(user: User) {
            binding.tvUsername.text = user.login
            binding.tbFavorite.isChecked = user.isFavorite
            Glide.with(binding.root)
                .load(user.avatarUrl)
                .into(binding.ivAvatar)
        }
    }
}

private class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}
