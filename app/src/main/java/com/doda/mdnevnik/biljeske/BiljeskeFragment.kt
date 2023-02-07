package com.doda.mdnevnik.biljeske

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doda.mdnevnik.R
import com.doda.mdnevnik.databinding.FragmentBiljeskeBinding

class BiljeskeFragment : Fragment() {

    var _binding: FragmentBiljeskeBinding? = null

    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBiljeskeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigation.selectedItemId = R.id.biljeske_item
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}