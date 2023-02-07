package com.doda.mdnevnik.razredi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doda.mdnevnik.MdnevnikApplication
import com.doda.mdnevnik.Ocjena
import com.doda.mdnevnik.databinding.FragmentOcjeneBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OcjeneBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentOcjeneBottomSheetBinding? = null

    private val binding get() = _binding!!

    val ocjene: List<Ocjena> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOcjeneBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = (activity?.application as MdnevnikApplication).database

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}