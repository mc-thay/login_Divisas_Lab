package com.aguilar.login_divisas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.p1alab3.CurrencyConverterViewModel

class ConvDivisas : Fragment() {

    private lateinit var viewModel: CurrencyConverterViewModel
    private lateinit var inputAmount: EditText
    private lateinit var convertButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var conversionTypeSpinner: Spinner
    private lateinit var logoutButton: Button // Añadido para el botón de cerrar sesión

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_conv_divisas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputAmount = view.findViewById(R.id.inputSoles)
        convertButton = view.findViewById(R.id.convertButton)
        resultTextView = view.findViewById(R.id.resultTextView)
        conversionTypeSpinner = view.findViewById(R.id.conversionTypeSpinner)
        logoutButton = view.findViewById(R.id.logoutButton) // Inicializa el botón de cerrar sesión

        viewModel = ViewModelProvider(this).get(CurrencyConverterViewModel::class.java)

        convertButton.setOnClickListener {
            val amount = inputAmount.text.toString()
            val conversionType = conversionTypeSpinner.selectedItem.toString()
            viewModel.convertCurrency(amount, conversionType)
        }

        viewModel.conversionResult.observe(viewLifecycleOwner) { result ->
            resultTextView.text = result?.let { "$it" } ?: "Error en la conversión"
        }

        // Configurar el botón de cerrar sesión
        logoutButton.setOnClickListener {
            // Aquí puedes manejar la lógica para cerrar sesión
            // Por ejemplo, navegar de vuelta al fragmento de login:
            findNavController().navigate(R.id.action_convDivisas_to_login) // Asegúrate de tener esta acción definida en tu grafo de navegación
        }
    }
}
