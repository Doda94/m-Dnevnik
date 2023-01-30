package com.doda.e_dnevnik.razredi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.doda.e_dnevnik.EdnevnikApplication
import com.doda.e_dnevnik.api.ApiModule
import com.doda.e_dnevnik.databinding.FragmentRazrediBinding
import com.doda.e_dnevnik.db.OcjeneEntity
import com.doda.e_dnevnik.preferences.MyPreferences

class RazrediFragment : Fragment() {

    private var _binding: FragmentRazrediBinding? = null

    private val binding get() = _binding!!

    private lateinit var sharedPreferences: MyPreferences

    private lateinit var adapter: PredmetiAdapter

    private lateinit var predmeti: List<PredmetEntity>

    private lateinit var ocjene: List<OcjeneEntity>

    private val args by navArgs<RazrediFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRazrediBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        predmeti = emptyList()
        ocjene = emptyList()

        val viewModel: PredmetiViewModel by viewModels{
            PredmetiViewModelFactory((activity?.application as EdnevnikApplication).database)
        }

        val database = (activity?.application as EdnevnikApplication).database

        sharedPreferences = MyPreferences(requireContext())

        ApiModule.initRetrofit(sharedPreferences)
        viewModel.loadPredmete(args.id, args.razred)

        viewModel.predmetiLiveData.observe(viewLifecycleOwner) {
            predmeti = it
            initPredmetiRecycler()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initPredmetiRecycler() {
        adapter = PredmetiAdapter(predmeti, ocjene) { predmet ->
//            TODO: finish this
        }
        binding.ocjeneRecyclerView.layoutManager = LinearLayoutManager(activity)

        binding.ocjeneRecyclerView.adapter = adapter
    }

}