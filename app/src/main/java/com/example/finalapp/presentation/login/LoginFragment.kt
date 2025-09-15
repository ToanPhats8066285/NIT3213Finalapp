package com.example.finalapp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.finalapp.R
import com.example.finalapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val vm: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val edtUser = view.findViewById<EditText>(R.id.edtUsername)
        val edtPass = view.findViewById<EditText>(R.id.edtPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val progress = view.findViewById<ProgressBar>(R.id.progress)

        // You can replace this with a real selector (Spinner/TextInput...).
        // For now we use a fixed campus, because your API path requires it.
        val campus = "footscray"

        btnLogin.setOnClickListener {
            val username = edtUser.text?.toString()?.trim().orEmpty()
            val password = edtPass.text?.toString()?.trim().orEmpty()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Call ViewModel with THREE params: campus + username + password
            vm.login(campus, username, password)
        }

        // Observe login state
        vm.state.observe(viewLifecycleOwner) { res ->
            when (res) {
                is Resource.Loading -> {
                    progress.isVisible = true
                    btnLogin.isEnabled = false
                }

                is Resource.Success -> {
                    progress.isVisible = false
                    btnLogin.isEnabled = true

                    val keypass = res.data?.keypass
                    if (!keypass.isNullOrEmpty()) {
                        // Navigate to Dashboard with keypass
                        findNavController().navigate(
                            R.id.action_login_to_dashboard,
                            bundleOf("keypass" to keypass)
                        )
                    } else {
                        Toast.makeText(requireContext(), "Invalid response", Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Error -> {
                    progress.isVisible = false
                    btnLogin.isEnabled = true
                    Toast.makeText(
                        requireContext(),
                        res.message ?: "Login failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
