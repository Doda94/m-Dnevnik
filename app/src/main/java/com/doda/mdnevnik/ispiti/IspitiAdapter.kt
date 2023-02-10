package com.doda.mdnevnik.ispiti

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doda.mdnevnik.DatumConverter
import com.doda.mdnevnik.databinding.ViewItemIspitBinding
import com.doda.mdnevnik.db.IspitiEntity

class IspitiAdapter(
    private var ispiti: List<IspitiEntity>,
    private var onItemClickCallback: (IspitiEntity) -> Unit
): RecyclerView.Adapter<IspitiAdapter.IspitiViewHolder>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IspitiViewHolder {
                val binding = ViewItemIspitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return IspitiViewHolder(binding)
            }

            override fun onBindViewHolder(holder: IspitiViewHolder, position: Int) {
                holder.bind(ispiti[position])
            }

            override fun getItemCount(): Int = ispiti.size

            inner class IspitiViewHolder(private val binding: ViewItemIspitBinding) : RecyclerView.ViewHolder(binding.root) {
                fun bind(item: IspitiEntity) {
                    binding.biljeskaTv.text = item.ispitInfo.biljeska
                    binding.predmetTv.text = item.ispitInfo.predmet
                    binding.datumTv.text = DatumConverter.getDatum(item.ispitInfo.datum)
                }
            }
}