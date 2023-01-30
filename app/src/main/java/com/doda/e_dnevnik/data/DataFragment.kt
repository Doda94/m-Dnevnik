package com.doda.e_dnevnik.data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.doda.e_dnevnik.api.ApiModule
import com.doda.e_dnevnik.databinding.FragmentDataBinding
import com.doda.e_dnevnik.preferences.MyPreferences

class DataFragment : Fragment() {

    private var _binding: FragmentDataBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: DataViewModel by viewModels()

    private lateinit var sharedPreferences: MyPreferences

    private lateinit var adapter: DataAdapter

    private lateinit var razredi: Array<Razred>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.razrediLiveData.observe(viewLifecycleOwner) { items ->
            razredi = items
            initRazrediRecycler()
        }
        sharedPreferences = MyPreferences(requireContext())

        ApiModule.initRetrofit(sharedPreferences)
        viewModel.loadRazrede()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    private fun initRazrediRecycler(){
            adapter = DataAdapter(razredi) { razred ->
                val directions = DataFragmentDirections.actionDataFragmentToRazrediFragment(razred.ed_id, razred.name)
                findNavController().navigate(directions)
            }
            binding.razrediRecyclerView.layoutManager = LinearLayoutManager(activity)

            binding.razrediRecyclerView.adapter = adapter
    }
}