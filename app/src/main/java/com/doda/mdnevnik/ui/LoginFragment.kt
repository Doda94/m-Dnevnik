package com.doda.mdnevnik.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.doda.mdnevnik.R
import com.doda.mdnevnik.api.ApiModule
import com.doda.mdnevnik.databinding.FragmentLoginBinding
import com.doda.mdnevnik.login.LoginViewModel
import com.doda.mdnevnik.preferences.MyPreferences
import com.google.android.material.snackbar.Snackbar

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
            if (!email.contains("@")){
                email+= "@skole.hr"
            }
            viewModel.onLoginButtonClicked(email, password, sharedPreferences)
        }
    }

    private fun initLoginLiveDataObserver() {
        viewModel.getLoginResultLiveData().observe(viewLifecycleOwner) { isSuccessful ->
            if (isSuccessful == 1) {
                val directions = LoginFragmentDirections.actionLoginFragmentToDataFragment()
                findNavController().navigate(directions)
            } else {
                if (isSuccessful == 2) {
                    Snackbar.make(
                        binding.root,
                        R.string.login_failed_server,
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    Snackbar.make(
                        binding.root,
                        R.string.login_failed_creds,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


}