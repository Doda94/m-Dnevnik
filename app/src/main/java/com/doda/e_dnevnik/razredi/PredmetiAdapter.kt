package com.doda.e_dnevnik.razredi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doda.e_dnevnik.databinding.ViewItemPredmetBinding
import com.doda.e_dnevnik.db.OcjeneEntity

class PredmetiAdapter(
    private var predmeti: List<PredmetEntity>,
    private var ocjene: List<OcjeneEntity>,
    private var onItemClickCallback: (PredmetEntity) -> Unit
) : RecyclerView.Adapter<PredmetiAdapter.PredmetiViewHolder>
() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PredmetiViewHolder {
        val binding = ViewItemPredmetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PredmetiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PredmetiViewHolder, position: Int) {
        holder.bind(predmeti[position], ocjene[position])
    }

    override fun getItemCount(): Int = predmeti.size

    inner class PredmetiViewHolder(private val binding: ViewItemPredmetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PredmetEntity, itemProsjek: OcjeneEntity) {
            binding.predmetTitle.text = item.predmet.subject
            binding.predmetTeacher.text = item.predmet.teachersName
            binding.predmetAverage.text = itemProsjek.prosjek
            binding.predmetCard.setOnClickListener { onItemClickCallback(item) }
        }
    }

    fun update(items: List<PredmetEntity>, ocjeneItems: List<OcjeneEntity>) {
        predmeti = items
        ocjene = ocjeneItems
        notifyDataSetChanged()
    }
}

