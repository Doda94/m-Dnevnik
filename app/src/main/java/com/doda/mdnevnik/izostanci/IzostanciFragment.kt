package com.doda.mdnevnik.izostanci

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.doda.mdnevnik.MdnevnikApplication
import com.doda.mdnevnik.R
import com.doda.mdnevnik.databinding.FragmentIzostanciBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class IzostanciFragment : Fragment() {

    private var _binding: FragmentIzostanciBinding? = null

    private val binding get() = _binding!!

    var izostanci: List<Izostanak> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIzostanciBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigation.selectedItemId = R.id.izostanci_item

        initBottomNavBarListener()

        val viewModel: IzostanciViewModel by viewModels {
            IzostanciViewModelFactory((activity?.application as MdnevnikApplication).database)
        }

        viewModel.loadIzostanke()

        viewModel.izostanciLiveData.observe(viewLifecycleOwner) {
            val listaDatuma = it.sortedWith(compareBy{it.datum})
            for (izostanak in listaDatuma) {
                val izostanciIzListe = izostanak.izostanci.sortedWith(compareBy{it.izostanakInfo.classNumber})
                for (_izostanak in izostanciIzListe){
                    izostanci += _izostanak.izostanakInfo
                }
            }
            initIzostanciRecycler()
        }

        binding.floatingActionButton.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.legenda))
                .setView(R.layout.dialog_izostanci)
                .show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initBottomNavBarListener() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ispiti_item -> {
                    val directions = IzostanciFragmentDirections.actionIzostanciFragmentToIspitiFragment()
                    findNavController().navigate(directions)
                    true
                }
                R.id.biljeske_item -> {
                    val directions = IzostanciFragmentDirections.actionIzostanciFragmentToBiljeskeFragment()
                    findNavController().navigate(directions)
                    true
                }
                R.id.ponasanje_item -> {
                    val directions = IzostanciFragmentDirections.actionIzostanciFragmentToVladanjeFragment()
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

    private fun initIzostanciRecycler() {
        val adapter: IzostanciAdapter = IzostanciAdapter(izostanci) {}

        binding.izostanciRecyclerView.adapter = adapter

        binding.izostanciRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

}