package com.doda.mdnevnik.razredi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doda.mdnevnik.databinding.ViewItemPredmetBinding
import com.doda.mdnevnik.db.OcjeneEntity
import com.doda.mdnevnik.db.PredmetEntity

class PredmetiAdapter(
    private var predmeti: List<PredmetEntity>,
    private var onItemClickCallback: (PredmetEntity) -> Unit
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
        fun bind(item: PredmetEntity) {
            binding.predmetTitle.text = item.predmet.subject
            binding.predmetTeacher.text = item.predmet.teachersName
            if (item.prosjek == null)
                binding.predmetAverage.text = "0,00"
            else
                binding.predmetAverage.text = String.format("%.2f", item.prosjek).replace(".",",")
            binding.predmetCard.setOnClickListener { onItemClickCallback(item) }
        }
    }

    fun update(items: List<PredmetEntity>, ocjeneItems: List<OcjeneEntity>) {
        predmeti = items
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position
}

