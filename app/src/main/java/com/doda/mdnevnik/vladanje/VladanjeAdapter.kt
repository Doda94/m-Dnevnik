package com.doda.mdnevnik.vladanje

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doda.mdnevnik.databinding.ViewItemBiljeskaBinding
import com.doda.mdnevnik.db.VladanjeEntity

class VladanjeAdapter(
    private var vladanje: List<VladanjeEntity>,
    private var onItemClickCallback: (VladanjeEntity) -> Unit
) : RecyclerView.Adapter<VladanjeAdapter.VladanjeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VladanjeViewHolder {
        val binding = ViewItemBiljeskaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VladanjeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VladanjeViewHolder, position: Int) {
        holder.bind(vladanje[position])
    }

    override fun getItemCount(): Int = vladanje.size

    inner class VladanjeViewHolder(private val binding: ViewItemBiljeskaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VladanjeEntity) {
            binding.titleTv.text = item.title
            // TODO: Fix prikazivanje teksta
            binding.dataTv.text = item.dataText
        }
    }

    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position
}