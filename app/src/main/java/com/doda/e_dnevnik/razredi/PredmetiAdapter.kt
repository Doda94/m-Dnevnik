package com.doda.e_dnevnik.razredi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doda.e_dnevnik.databinding.ViewItemPredmetBinding

class PredmetiAdapter(
    private var predmeti: List<Predmet>,
    private var onItemClickCallback: (Predmet) -> Unit
) : RecyclerView.Adapter<PredmetiAdapter.PredmetiViewHolder>
() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PredmetiViewHolder {
        val binding = ViewItemPredmetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PredmetiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PredmetiViewHolder, position: Int) {
        holder.bind(predmeti[position])
    }

    override fun getItemCount(): Int = predmeti.size

    inner class PredmetiViewHolder(private val binding: ViewItemPredmetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Predmet) {
            binding.predmetTitle.text = item.subject
            binding.predmetTeacher.text = item.teachersName
            binding.predmetAverage.text = "5,0"
            binding.predmetCard.setOnClickListener { onItemClickCallback(item) }
        }
    }

    fun loadPredmete(items: List<Predmet>) {
        predmeti = items
        notifyDataSetChanged()
    }
}

