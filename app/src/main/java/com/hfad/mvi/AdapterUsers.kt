package com.hfad.mvi


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hfad.mvi.data.Users
import com.hfad.mvi.databinding.ItemUsersBinding

class AdapterUsers(
    private var listener: OnClickListener,
    private var users: ArrayList<Users>
    ) : RecyclerView.Adapter<AdapterUsers.ViewHolder>() {

   inner class ViewHolder(private var binding: ItemUsersBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("CheckResult")
        fun bindView(users: Users) {
            with(binding) {
                idTextView.text = users.id.toString()
                loginTextView.text = users.login
                Glide
                    .with(itemView)
                    .load(users.avatar_url)
                    .into(imageView)

                itemView.setOnClickListener {
                    listener.onClickUser(users.login)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUsersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    // TODO: Функция для добавления эл-ов.В Main Activity
    fun addData(list: List<Users>) {
        users.addAll(list)
    }

    interface OnClickListener{
        fun onClickUser(login:String)
    }
}