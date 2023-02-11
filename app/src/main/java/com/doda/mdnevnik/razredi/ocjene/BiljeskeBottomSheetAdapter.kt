package com.doda.mdnevnik.razredi.ocjene

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doda.mdnevnik.DatumConverter
import com.doda.mdnevnik.Ocjena
import com.doda.mdnevnik.databinding.ViewItemOcjenaBiljeskaBinding

class BiljeskeBottomSheetAdapter(
    private var ocjene: List<Ocjena>,
    private var onItemClickCallback: (Ocjena) -> Unit
) : RecyclerView.Adapter<BiljeskeBottomSheetAdapter.BiljeskeBottomSheetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BiljeskeBottomSheetViewHolder {
        val binding = ViewItemOcjenaBiljeskaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BiljeskeBottomSheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BiljeskeBottomSheetViewHolder, position: Int) {
        holder.bind(ocjene[position])
    }

    override fun getItemCount(): Int = ocjene.size

    inner class BiljeskeBottomSheetViewHolder(private val binding: ViewItemOcjenaBiljeskaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Ocjena) {
            binding.datumTv.text = DatumConverter.getDatum(item.date.toLong())
            binding.biljeskaTv.text = item.note
            binding.ocjenaBiljeskaCard.setOnClickListener { onItemClickCallback(item) }
        }
    }

    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position
}
