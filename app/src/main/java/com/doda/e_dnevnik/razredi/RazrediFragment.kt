package com.doda.e_dnevnik.razredi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.doda.e_dnevnik.api.ApiModule
import com.doda.e_dnevnik.databinding.FragmentRazrediBinding
import com.doda.e_dnevnik.preferences.MyPreferences

class RazrediFragment : Fragment() {

    private var _binding: FragmentRazrediBinding? = null

    private val binding get() = _binding!!

    private val viewModel: PredmetiViewModel by viewModels()

    private lateinit var sharedPreferences: MyPreferences

    private lateinit var adapter: PredmetiAdapter

    private lateinit var predmeti: List<Predmet>

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

        viewModel.predmetiLiveData.observe(viewLifecycleOwner) { items ->
            predmeti = items
            initPredmetiRecycler()
        }
        sharedPreferences = MyPreferences(requireContext())

        ApiModule.initRetrofit(sharedPreferences)
        viewModel.loadPredmete(args.id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initPredmetiRecycler() {
        adapter = PredmetiAdapter(predmeti) { predmet ->
//            TODO: finish this
        }
        binding.ocjeneRecyclerView.layoutManager = LinearLayoutManager(activity)

        binding.ocjeneRecyclerView.adapter = adapter
    }

}