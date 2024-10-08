package com.doda.mdnevnik.razredi.ocjene

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.doda.mdnevnik.DatumConverter
import com.doda.mdnevnik.MdnevnikApplication
import com.doda.mdnevnik.Ocjena
import com.doda.mdnevnik.databinding.FragmentOcjeneBottomSheetBinding
import com.doda.mdnevnik.razredi.Rubrika
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class OcjeneBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentOcjeneBottomSheetBinding? = null

    private val binding get() = _binding!!

    var ocjene: List<Ocjena> = listOf()

    var biljeske: List<Ocjena> = listOf()

    private val args by navArgs<OcjeneBottomSheetFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOcjeneBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: OcjeneBottomSheetViewModel by viewModels {
            OcjeneBottomSheetViewModelFactory((activity?.application as MdnevnikApplication).database)
        }

        viewModel.loadOcjene(args.id)

        viewModel.ocjeneLiveData.observe(viewLifecycleOwner) {
            for (rubrika in it) {
                if (rubrika.name == "") {
                    biljeske += rubrika.ocjene
                } else {
                    ocjene += rubrika.ocjene
                }
            }
            initOcjeneRecycler()
            initBiljeskeRecycler()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initOcjeneRecycler() {
        val adapter: OcjeneBottomSheetAdapter = OcjeneBottomSheetAdapter(ocjene) {ocjena ->
            var title: String = getOpisOcjene(ocjena.grade)
            title+= " - " + ocjena.date
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getOpisOcjene(ocjena.grade) + " - " + DatumConverter.getDugiDatum(ocjena.date.toLong()))
                .setMessage(ocjena.note)
                .setNeutralButton("Zatvori") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
        binding.recyclerView.adapter = adapter

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun initBiljeskeRecycler() {
        binding.biljeskeHeaderTv.isVisible = biljeske.isNotEmpty()
        val Biljeskeadapter: BiljeskeBottomSheetAdapter = BiljeskeBottomSheetAdapter(biljeske) {
            it
        }
        binding.recyclerViewBiljeske.adapter = Biljeskeadapter

        binding.recyclerViewBiljeske.layoutManager = LinearLayoutManager(activity)
    }

    private fun getOpisOcjene(ocjena: String): String {
        when (ocjena) {
            "1" -> {
                return "nedovoljan (1)"
            }
            "2" -> {
                return "dovoljan (2)"
            }
            "3" -> {
                return "dobar (3)"
            }
            "4" -> {
                return "vrlo dobar (4)"
            }
            "5" -> {
                return "odličan (5)"
            }
        }
        return ""
    }

}