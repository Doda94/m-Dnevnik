package com.doda.mdnevnik.razredi.ocjene

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doda.mdnevnik.Ocjena
import com.doda.mdnevnik.databinding.ViewItemHeaderBinding
import com.doda.mdnevnik.databinding.ViewItemOcjenaBinding
import com.doda.mdnevnik.razredi.Rubrika
import java.util.Date
import java.util.concurrent.TimeUnit

class OcjeneBottomSheetAdapter(
    private var ocjene: List<Ocjena>,
    private var onItemClickCallback: (Ocjena) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder
        if (getItemViewType(position) == 0) {
            (holder as HeaderViewHolder).bind(ocjene[position])
        } else {
            (holder as OcjeneViewHolder).bind(ocjene[position])
        }

    }

    private inner class HeaderViewHolder(private val binding: ViewItemHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ocjena: Ocjena) {
            val vrijeme = TimeUnit.SECONDS.toMillis(ocjena.date.toLong())
            val date = Date(vrijeme)
            binding.headerTv.text = ocjena.field
            binding.ocjenaTv.text = ocjena.grade
            binding.datumTv.text = date.toString()
            binding.ocjenaSlovimaTv.text = getOpisOcjene(ocjena.grade)
        }
    }

    private inner class OcjeneViewHolder(private val binding: ViewItemOcjenaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Ocjena) {
            val vrijeme = TimeUnit.SECONDS.toMillis(item.date.toLong())
            val date = Date(vrijeme)
            binding.datumTv.text = date.toString()
            binding.ocjenaTv.text = item.grade
            binding.ocjenaSlovimaTv.text = getOpisOcjene(item.grade)
        }
    }

    private fun getOpisOcjene(ocjena: String): String {
        when (ocjena) {
            "1" -> {
                return "nedovoljan"
            }
            "2" -> {
                return "dovoljan"
            }
            "3" -> {
                return "dobar"
            }
            "4" -> {
                return "vrlo dobar"
            }
            "5" -> {
                return "odličan"
            }
        }
        return ""
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            val binding = ViewItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
}