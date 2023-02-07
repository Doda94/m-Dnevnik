package com.doda.mdnevnik.biljeske

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doda.mdnevnik.databinding.ViewItemBiljeskaBinding
import com.doda.mdnevnik.db.BiljeskaEntity

class BiljeskeAdapter(
    private var biljeske: List<BiljeskaEntity>,
    private var onItemClickCallback: (BiljeskaEntity) -> Unit
): RecyclerView.Adapter<BiljeskeAdapter.BiljeskeViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BiljeskeViewHolder {
            val binding = ViewItemBiljeskaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return BiljeskeViewHolder(binding)
        }

        override fun onBindViewHolder(holder: BiljeskeViewHolder, position: Int) {
            holder.bind(biljeske[position])
        }

        override fun getItemCount(): Int = biljeske.size

        inner class BiljeskeViewHolder(private val binding: ViewItemBiljeskaBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(item: BiljeskaEntity) {
                binding.titleTv.text = item.title
                binding.dataTv.text = item.dataText
            }
        }

}