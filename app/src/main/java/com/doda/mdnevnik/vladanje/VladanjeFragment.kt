package com.doda.mdnevnik.vladanje

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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

}