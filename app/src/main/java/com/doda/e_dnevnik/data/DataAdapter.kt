package com.doda.e_dnevnik.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doda.e_dnevnik.databinding.ViewItemRazredBinding

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
        }
    }

    fun loadRazrede(items: Array<Razred>){
        razredi = items
        notifyDataSetChanged()
    }

}