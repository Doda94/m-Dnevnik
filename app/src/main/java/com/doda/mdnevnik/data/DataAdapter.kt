package com.doda.mdnevnik.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doda.mdnevnik.databinding.ViewItemRazredBinding

class DataAdapter(
    private var razredi: Array<Razred>,
    private var onItemClickCallback: (Razred) -> Unit
) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ViewItemRazredBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(razredi[position])
    }

    override fun getItemCount(): Int = razredi.size

    inner class DataViewHolder(private val binding: ViewItemRazredBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Razred) {
            binding.razredTitle.text = item.name
            binding.razredGen.text = item.gen
            binding.razredCard.setOnClickListener { onItemClickCallback(item) }
        }


    }

    fun loadRazrede(items: Array<Razred>) {
        razredi = items
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position

}