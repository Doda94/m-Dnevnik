package com.doda.mdnevnik.razredi.ocjene

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doda.mdnevnik.DatumConverter
import com.doda.mdnevnik.Ocjena
import com.doda.mdnevnik.databinding.ViewItemOcjenaBiljeskaBinding
import com.doda.mdnevnik.databinding.ViewItemOcjenaBinding
import com.doda.mdnevnik.databinding.ViewItemOcjenaHeaderBinding

class OcjeneBottomSheetAdapter(
    private var ocjene: List<Ocjena>,
    private var onItemClickCallback: (Ocjena) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 0) {
            (holder as HeaderViewHolder).bind(ocjene[position])
        } else {
            (holder as OcjeneViewHolder).bind(ocjene[position])
        }
    }

    private inner class HeaderViewHolder(private val binding: ViewItemOcjenaHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ocjena: Ocjena) {
            binding.headerTv.text = ocjena.field
            binding.ocjenaLayout.ocjenaTv.text = ocjena.grade
            binding.ocjenaLayout.datumTv.text = DatumConverter.getDatum(ocjena.date.toLong())
            binding.ocjenaLayout.ocjenaSlovimaTv.text = getOpisOcjene(ocjena.grade)
        }
    }

    private inner class OcjeneViewHolder(private val binding: ViewItemOcjenaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Ocjena) {
            binding.datumTv.text = DatumConverter.getDatum(item.date.toLong())
            binding.ocjenaTv.text = item.grade
            binding.ocjenaSlovimaTv.text = getOpisOcjene(item.grade)
        }
    }

    private fun getOpisOcjene(ocjena: String): String {
        when (ocjena) {
            "1" -> {
                return "nedovoljan (1)"
            }
            "2" -> {
                return "dovoljan (2)"
            }
            "3" -> {
                return "dobar (3)"
            }
            "4" -> {
                return "vrlo dobar (4)"
            }
            "5" -> {
                return "odličan (5)"
            }
        }
        return ""
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            val binding = ViewItemOcjenaHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return HeaderViewHolder(binding)
        } else {
            val binding = ViewItemOcjenaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return OcjeneViewHolder(binding)
        }
    }

    override fun getItemCount(): Int {
        return ocjene.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return 0
        }
        if (ocjene[position].field != ocjene[position - 1].field) {
            return 0
        } else {
            return 1
        }
    }

    override fun getItemId(position: Int) = position.toLong()
}