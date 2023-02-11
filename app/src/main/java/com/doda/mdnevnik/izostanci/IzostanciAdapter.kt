package com.doda.mdnevnik.izostanci

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.doda.mdnevnik.DatumConverter
import com.doda.mdnevnik.R
import com.doda.mdnevnik.databinding.ViewItemIzostanakBinding
import com.doda.mdnevnik.databinding.ViewItemIzostanakHeaderBinding

class IzostanciAdapter(
    private var izostanci: List<Izostanak>,
    private var onItemClickCallback: (Izostanak) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private inner class HeaderViewHolder(private val binding: ViewItemIzostanakHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(izostanak: Izostanak) {
            binding.headerTv.text = DatumConverter.getDatum(izostanak.date)
            binding.izostanakLayout.redniBrojTv.text = izostanak.classNumber.toString()
            binding.izostanakLayout.predmetTv.text = izostanak.subject
            if (izostanak.status== "icon-circle red")
                binding.izostanakLayout.razlogImageView.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, R.drawable.red_baseline_circle_24))
            else if (izostanak.status == "icon-circle green")
                binding.izostanakLayout.razlogImageView.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, R.drawable.green_baseline_circle_24))
            else if (izostanak.status == "icon-circle gray")
                binding.izostanakLayout.razlogImageView.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, R.drawable.gray_baseline_circle_24))
            else if (izostanak.status == "icon-circle black")
                binding.izostanakLayout.razlogImageView.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, R.drawable.black_baseline_circle_24))
            else if (izostanak.status == "icon-circle yellow")
                binding.izostanakLayout.razlogImageView.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, R.drawable.yellow_baseline_circle_24))
        }
    }

    private inner class IzostanciViewHolder(private val binding: ViewItemIzostanakBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Izostanak) {
            binding.predmetTv.text = item.subject
            if (item.status == "icon-circle red")
                binding.razlogImageView.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, R.drawable.red_baseline_circle_24))
            else if (item.status == "icon-circle green")
                binding.razlogImageView.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, R.drawable.green_baseline_circle_24))
            else if (item.status == "icon-circle gray")
                binding.razlogImageView.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, R.drawable.gray_baseline_circle_24))
            else if (item.status == "icon-circle black")
                binding.razlogImageView.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, R.drawable.black_baseline_circle_24))
            else if (item.status == "icon-circle yellow")
                binding.razlogImageView.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, R.drawable.yellow_baseline_circle_24))

            binding.redniBrojTv.text = item.classNumber.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val binding = ViewItemIzostanakHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            HeaderViewHolder(binding)
        } else {
            val binding = ViewItemIzostanakBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            IzostanciViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 0) {
            (holder as HeaderViewHolder).bind(izostanci[position])
        } else {
            (holder as IzostanciViewHolder).bind(izostanci[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return 0
        }
        if (izostanci[position].date == izostanci[position - 1].date && izostanci[position].classNumber > izostanci[position - 1].classNumber) {
            return 1
        } else {
            return 0
        }
    }

    override fun getItemCount(): Int {
        return izostanci.size
    }

    override fun getItemId(position: Int) = position.toLong()

}