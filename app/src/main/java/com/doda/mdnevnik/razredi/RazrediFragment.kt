package com.doda.mdnevnik.razredi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.doda.mdnevnik.MdnevnikApplication
import com.doda.mdnevnik.R
import com.doda.mdnevnik.api.ApiModule
import com.doda.mdnevnik.databinding.FragmentRazrediBinding
import com.doda.mdnevnik.db.OcjeneEntity
import com.doda.mdnevnik.db.PredmetEntity
import com.doda.mdnevnik.preferences.MyPreferences
import kotlin.math.roundToInt

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
        binding.bottomNavigation.selectedItemId = R.id.predmeti_item
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigation.selectedItemId = R.id.predmeti_item

        predmeti = emptyList()
        ocjene = emptyList()

        val database = (activity?.application as MdnevnikApplication).database

        val viewModel: PredmetiViewModel by viewModels {
            PredmetiViewModelFactory((activity?.application as MdnevnikApplication).database)
        }

        sharedPreferences = MyPreferences(requireContext())

        ApiModule.initRetrofit(sharedPreferences)
        viewModel.loadPredmete(args.id)
        viewModel.loadIspiti(args.id)
        initBottomNavBarListener()

        viewModel.predmetiLiveData.observe(viewLifecycleOwner) {
            predmeti = it
            predmeti = predmeti.sortedBy { predmet -> predmet.predmet.subject }
            var prosjek = 0.0
            var brojOcjena = 0
            for (predmet in predmeti) {
                if (predmet.prosjek != null) {
                    if (predmet.prosjek != 0.0) {
                        prosjek += predmet.prosjek!!.roundToInt()
                        brojOcjena++
                    }
                }
            }
            if (brojOcjena != 0) {
                prosjek /= brojOcjena
                var prosjekString = String.format("%.2f", prosjek).replace(".",",")
                binding.toolbar.title = "Ukupni prosjek: ${prosjekString}"
            }
            initPredmetiRecycler()
        }

        viewModel.numberOfProsjekLiveData.observe(viewLifecycleOwner) {
            if (it == predmeti.size && it != 0) {
                hideProgressIndicator()
                viewModel.loadBiljeske(args.id)
                viewModel.loadVladanje(args.id)
                viewModel.loadIzostanke(args.id)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        binding.bottomNavigation.selectedItemId = R.id.predmeti_item
    }

    private fun initPredmetiRecycler() {
        adapter = PredmetiAdapter(predmeti) { predmet ->
            val directions = RazrediFragmentDirections.actionRazrediFragmentToOcjeneBottomSheetFragment(predmet.predmetId)
            findNavController().navigate(directions)
        }
        binding.ocjeneRecyclerView.layoutManager = LinearLayoutManager(activity)

        binding.ocjeneRecyclerView.adapter = adapter
    }

    private fun hideProgressIndicator() {
        binding.progressIndicator.visibility = View.GONE
        binding.appBarLayout.visibility = View.VISIBLE
        binding.nestedScrollView.visibility = View.VISIBLE
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    private fun initBottomNavBarListener() {
        binding.bottomNavigation.selectedItemId = R.id.predmeti_item
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ispiti_item -> {
                    val directions = RazrediFragmentDirections.actionRazrediFragmentToIspitiFragment()
                    findNavController().navigate(directions)
                    true
                }
                R.id.izostanci_item -> {
                    val directions = RazrediFragmentDirections.actionRazrediFragmentToIzostanciFragment()
                    findNavController().navigate(directions)
                    true
                }
                R.id.biljeske_item -> {
                    val directions = RazrediFragmentDirections.actionRazrediFragmentToBiljeskeFragment()
                    findNavController().navigate(directions)
                    true
                }
                R.id.ponasanje_item -> {
                    val directions = RazrediFragmentDirections.actionRazrediFragmentToVladanjeFragment()
                    findNavController().navigate(directions)
                    true
                }
                else -> false
            }
        }
    }

}