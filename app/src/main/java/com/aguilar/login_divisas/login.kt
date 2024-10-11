package com.aguilar.login_divisas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.aguilar.login_divisas.databinding.FragmentLoginBinding

class Login : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val userName = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (userName.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Rellena los campos", Toast.LENGTH_LONG).show()
            } else if (userName != "Elquin" || password != "Aguilar") {
                // Si el nombre de usuario o la contraseña no coinciden, mostrar mensaje de error
                Toast.makeText(requireContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
            } else {
                // Si el nombre de usuario y la contraseña son correctos, navegar a ConvDivisas
                findNavController().navigate(
                    R.id.action_login_to_convDivisas,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.login, true) // Cambia este ID según tu grafo de navegación
                        .build()
                )
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
