package com.doda.mdnevnik.biljeske

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.doda.mdnevnik.MdnevnikApplication
import com.doda.mdnevnik.R
import com.doda.mdnevnik.databinding.FragmentBiljeskeBinding
import com.doda.mdnevnik.db.BiljeskaEntity

class BiljeskeFragment : Fragment() {

    private var _binding: FragmentBiljeskeBinding? = null

    val binding get() = _binding!!

    private lateinit var adapter: BiljeskeAdapter

    private var biljeske = emptyList<BiljeskaEntity>()

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

        initBottomNavBarListener()

        val viewModel: BiljeskeViewModel by viewModels {
            BiljeskeViewModelFactory((activity?.application as MdnevnikApplication).database)
        }

        viewModel.loadBiljeske()

        viewModel.biljeskeLiveData.observe(viewLifecycleOwner) {
            biljeske = it
            initBiljeskeRecycler()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initBiljeskeRecycler() {
        adapter = BiljeskeAdapter(biljeske) {}

        binding.biljeskeRecyclerView.layoutManager = LinearLayoutManager(activity)

        binding.biljeskeRecyclerView.adapter = adapter
    }

    private fun initBottomNavBarListener() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ispiti_item -> {
                    val directions = BiljeskeFragmentDirections.actionBiljeskeFragmentToIspitiFragment()
                    findNavController().navigate(directions)
                    true
                }
                R.id.izostanci_item -> {
                    val directions = BiljeskeFragmentDirections.actionBiljeskeFragmentToIzostanciFragment()
                    findNavController().navigate(directions)
                    true
                }
                R.id.ponasanje_item -> {
                    val directions = BiljeskeFragmentDirections.actionBiljeskeFragmentToVladanjeFragment()
                    findNavController().navigate(directions)
                    true
                }
                R.id.predmeti_item -> {
                    findNavController().popBackStack(R.id.razrediFragment, false)
                    true
                }
                else -> false
            }
        }
    }

}