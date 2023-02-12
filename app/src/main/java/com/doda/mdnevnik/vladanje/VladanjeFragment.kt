package com.doda.mdnevnik.vladanje

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
import com.doda.mdnevnik.databinding.FragmentVladanjeBinding
import com.doda.mdnevnik.db.VladanjeEntity

class VladanjeFragment : Fragment() {

    private var _binding: FragmentVladanjeBinding? = null

    val binding get() = _binding!!

    private lateinit var adapter: VladanjeAdapter

    private var vladanja = emptyList<VladanjeEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVladanjeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigation.selectedItemId = R.id.ponasanje_item

        initBottomNavBarListener()

        val viewModel: VladanjeViewModel by viewModels{
            VladanjeViewModelFactory((activity?.application as MdnevnikApplication).database)
        }

        viewModel.getVladanje()

        viewModel.vladanjeLiveData.observe(viewLifecycleOwner){
            vladanja = it
            initVladanjeRecycler()
        }

    }


    private fun initVladanjeRecycler(){
        adapter = VladanjeAdapter(vladanja){}

        binding.vladanjeRecyclerView.layoutManager = LinearLayoutManager(activity)

        binding.vladanjeRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initBottomNavBarListener(){
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ispiti_item -> {
                    val directions = VladanjeFragmentDirections.actionVladanjeFragmentToIspitiFragment()
                    findNavController().navigate(directions)
                    true
                }
                R.id.izostanci_item -> {
                    val directions = VladanjeFragmentDirections.actionVladanjeFragmentToIzostanciFragment()
                    findNavController().navigate(directions)
                    true
                }
                R.id.biljeske_item -> {
                    val directions = VladanjeFragmentDirections.actionVladanjeFragmentToBiljeskeFragment()
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