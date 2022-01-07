package com.example.geekvisitmessage.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.geekvisitmessage.R
import com.example.geekvisitmessage.databinding.ItemIvBinding
import com.example.geekvisitmessage.databinding.ItemSendImageBinding
import com.example.geekvisitmessage.databinding.MassFragBinding
import com.example.geekvisitmessage.databinding.MassFragMeBinding

const val TEXT_RIGHT: Int = 0
const val TEXT_LEFT: Int = 1
const val IM_RIGHT: Int = 2
const val IM_LEFT: Int = 3

class ChatAdapter(
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: ArrayList<ChatModel> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun addText(list: ChatModel) {
        this.list.add(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.mass_frag ->
                MessageViewHolderSend(
                    MassFragBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            R.layout.mass_frag_me ->
                MessageViewHolderSendMe(
                    MassFragMeBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            R.layout.item_iv ->
                ViewHolderImageMe(
                    ItemIvBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            R.layout.item_send_image ->
                ViewHolderImageSend(
                    ItemSendImageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            else -> throw IllegalArgumentException("Wrong type of viewType= $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MessageViewHolderSend -> holder.bind(list[position])
            is MessageViewHolderSendMe -> holder.bind(list[position])
            is ViewHolderImageSend -> holder.bind(list[position])
            is ViewHolderImageMe -> holder.bind(list[position])
        }
//        if (list[position].message.equals("Hello")) {
//            val holder1 = holder as ViewHolderOne
//            holder1.bind(list[position])
//        } else {
//            val holder2 = holder as ViewHolderTwo
//            holder2.bind(list[position])
//        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position].type) {
            TEXT_RIGHT -> R.layout.mass_frag_me
            TEXT_LEFT -> R.layout.mass_frag
            IM_LEFT -> R.layout.item_iv
            IM_RIGHT -> R.layout.item_send_image
            else -> R.id.none
        }
    }

    inner class MessageViewHolderSend(private val binding: MassFragBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(s: ChatModel) {
            binding.textMessage.text = s.message


        }
    }

    inner class ViewHolderImageMe(private val binding: ItemIvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(s: ChatModel) {
            Glide.with(binding.imageViewMessageImage)
                .load(s.image)
                .centerCrop()
                .into(binding.imageViewMessageImage)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class MessageViewHolderSendMe(private val binding: MassFragMeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(s: ChatModel) {
            binding.sendTextMessage.text = s.message
        }
    }

    inner class ViewHolderImageSend(private val binding: ItemSendImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(s: ChatModel) {
            Glide.with(binding.imageViewMessageImageRecipent)
                .load(s.image)
                .centerCrop()
                .into(binding.imageViewMessageImageRecipent)
        }
    }
}