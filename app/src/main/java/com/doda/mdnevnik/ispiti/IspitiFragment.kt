package com.doda.mdnevnik.ispiti

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
import com.doda.mdnevnik.databinding.FragmentIspitiBinding
import com.doda.mdnevnik.db.IspitiEntity

class IspitiFragment : Fragment() {

    private var _binding: FragmentIspitiBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: IspitiAdapter

    private var ispiti = emptyList<IspitiEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIspitiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigation.selectedItemId = R.id.ispiti_item

        initBottomNavBarListener()

        val viewModel: IspitiViewModel by viewModels {
            IspitiViewModelFactory((activity?.application as MdnevnikApplication).database)
        }

        viewModel.loadIspiti()

        viewModel.ispitiLiveData.observe(viewLifecycleOwner) {
            ispiti = it
            initIspitiRecyclerView()
        }

    }

    fun initIspitiRecyclerView() {
        adapter = IspitiAdapter(ispiti) {}

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initBottomNavBarListener() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.izostanci_item -> {
                    val directions = IspitiFragmentDirections.actionIspitiFragmentToIzostanciFragment()
                    findNavController().navigate(directions)
                    true
                }
                R.id.biljeske_item -> {
                    val directions = IspitiFragmentDirections.actionIspitiFragmentToBiljeskeFragment()
                    findNavController().navigate(directions)
                    true
                }
                R.id.ponasanje_item -> {
                    val directions = IspitiFragmentDirections.actionIspitiFragmentToVladanjeFragment()
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