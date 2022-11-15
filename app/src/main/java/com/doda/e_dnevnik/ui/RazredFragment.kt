package com.doda.e_dnevnik.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.doda.e_dnevnik.api.ApiModule
import com.doda.e_dnevnik.data.DataViewModel
import com.doda.e_dnevnik.data.Razred
import com.doda.e_dnevnik.databinding.FragmentRazredBinding
import com.doda.e_dnevnik.preferences.MyPreferences

class RazredFragment : Fragment() {

    private var _binding: FragmentRazredBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: DataViewModel by viewModels()

    private lateinit var sharedPreferences: MyPreferences

    lateinit var razredi: Array<Razred>?

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRazredBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = MyPreferences(requireContext())

        ApiModule.initRetrofit(sharedPreferences)
        viewModel.loadRazrede()
        razredi = viewModel.getRazredi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}