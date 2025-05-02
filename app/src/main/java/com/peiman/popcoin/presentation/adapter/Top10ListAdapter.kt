package com.peiman.popcoin.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.peiman.popcoin.R
import com.peiman.popcoin.data.model.list.Top10Model
import com.peiman.popcoin.databinding.LayoutListItemBinding

class Top10ListAdapter : RecyclerView.Adapter<Top10ListAdapter.ViewHolder>() {

    private lateinit var context: Context
    val options = RequestOptions()
        .fitCenter()
        .placeholder(R.drawable.coin)
        .error(R.drawable.coin)

    private val callback = object : DiffUtil.ItemCallback<Top10Model>() {
        override fun areItemsTheSame(oldItem: Top10Model, newItem: Top10Model): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Top10Model, newItem: Top10Model): Boolean {
            return oldItem.name == newItem.name
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Top10ListAdapter.ViewHolder {
        context = parent.context
        val binding =
            LayoutListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: Top10ListAdapter.ViewHolder, position: Int) {
        val listItem = differ.currentList.get(position)
        holder.bind(listItem)
    }

    inner class ViewHolder(val binding: LayoutListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(top10Model: Top10Model) {
            Glide.with(context)
                .load(top10Model.image)
                .apply(options)
                .into(binding.listIcon)
            binding.listSymbol.text = top10Model.symbol
            binding.listName.text = top10Model.name
            binding.listPrice.text = "€"+top10Model.current_price.toString()
            binding.listHigh.text = "€"+top10Model.high_24h.toString()
            binding.listLow.text = "€"+top10Model.low_24h.toString()
            binding.listSupply.text = "€"+top10Model.total_supply.toString()

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(top10Model)
                }
            }
        }
    }

    private var onItemClickListener: ((Top10Model) -> Unit)? = null

    fun setOnItemClickListener(listener: (Top10Model) -> Unit) {
        onItemClickListener = listener
    }


}