package com.doda.e_dnevnik.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.doda.e_dnevnik.api.ApiModule
import com.doda.e_dnevnik.databinding.FragmentLoginBinding
import com.doda.e_dnevnik.login.LoginViewModel
import com.doda.e_dnevnik.preferences.MyPreferences

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: MyPreferences

    private val viewModel: LoginViewModel by viewModels()

    private var email = ""
    private var password = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = MyPreferences(requireContext())

        ApiModule.initRetrofit(sharedPreferences)
        initEmailListener()
        initPasswordListener()
        initLoginLiveDataObserver()
        initOnLoginBtnClickedListener()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initEmailListener() {
        binding.emailEditText.doOnTextChanged { text, _, _, _ ->
            email = text.toString()
            binding.loginBtn.isEnabled = email.isNotEmpty() && password.isNotEmpty()
        }
    }

    private fun initPasswordListener() {
        binding.passwordEditText.doOnTextChanged { text, _, _, _ ->
            password = text.toString()
            binding.loginBtn.isEnabled = email.isNotEmpty() && password.isNotEmpty()
        }
    }

    private fun initOnLoginBtnClickedListener() {
        binding.loginBtn.setOnClickListener {
            viewModel.onLoginButtonClicked(email, password, sharedPreferences)
        }
    }

    private fun initLoginLiveDataObserver() {
        viewModel.getLoginResultLiveData().observe(viewLifecycleOwner) { isSuccessful ->
            if (isSuccessful) {
                val directions = LoginFragmentDirections.actionLoginFragmentToRazredFragment()
                findNavController().navigate(directions)
            } else {
                Toast.makeText(requireContext(), "wronk", Toast.LENGTH_SHORT).show()
            }
        }
    }


}