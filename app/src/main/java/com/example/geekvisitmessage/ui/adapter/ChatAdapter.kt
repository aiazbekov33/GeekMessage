package com.example.geekvisitmessage.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.geekvisitmessage.databinding.MassFragBinding
import com.example.geekvisitmessage.databinding.MassFragMeBinding
import com.example.geekvisitmessage.databinding.MessageItemTextBinding

class ChatAdapter(
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: ArrayList<ChatModel> = ArrayList()

    val TEXT_RIGHT: Int = 1
    val TEXT_LEFT: Int = 2
    var fromMe: Boolean = false


    @SuppressLint("NotifyDataSetChanged")
    fun addText(list: ChatModel) {
        this.list.add(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        return if (viewType == 0) {
            ViewHolderOne(
                MessageItemTextBinding.inflate(
                    LayoutInflater.from(parent.context), parent,
                    false
                )
            )
        } else {
            ViewHolderTwo(
                MassFragMeBinding.inflate(
                    LayoutInflater.from(parent.context), parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].message.equals("Hello")) {
            val holder1 = holder as ViewHolderOne
            holder1.bind(list[position])
        } else {
            val holder2 = holder as ViewHolderTwo
            holder2.bind(list[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (list[position].message.equals("")) let {
            return if (it.fromMe) 1 else 2
        } else {
            return TEXT_LEFT
        }

    }


    inner class ViewHolderOne(private val binding: MessageItemTextBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(s: ChatModel) {
            binding.chatReceivedMessage.text = s.message
            binding.chatUserMessage.text = s.message


        }
    }

    inner class ViewHolderTwo(private val binding: MassFragMeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(s: ChatModel) {
            Glide.with(binding.chatUserImage)
                .load(s.image)
                .centerCrop()
                .into(binding.chatUserImage)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}