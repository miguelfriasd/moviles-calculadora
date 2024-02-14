package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var tvResultado: TextView
    private var num1: Double? = null
    private var num2: Double? = null
    private var signo: String? = null
    private var numActual: String = ""
    private var resultadoEnPantalla: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvResultado = findViewById(R.id.tvResult)
    }

    fun onNumeroClick(view: View) {
        if (resultadoEnPantalla){
            limpiar()
        }

        val numero = (view as TextView).text.toString()
        val resultadoActual = tvResultado.text.toString()
        if (resultadoActual == "0" || resultadoActual == "Error") {
            tvResultado.text = numero
            numActual = numero
            return
        }
        tvResultado.append(numero)
        numActual += numero
    }

    fun onPuntoClick(view : View){
        if (resultadoEnPantalla){
            limpiar()
            return
        }

        val punto = (view as TextView).text.toString()
        val resultadoActual = tvResultado.text.toString()
        if (numActual == "" || numActual.contains(".") || resultadoActual=="Error"){
            tvResultado.text = "Error"
            return
        }
        tvResultado.append(punto)
        numActual += punto
    }

    fun onOperadorClick(view: View) {
        if (resultadoEnPantalla){
            limpiar()
            return
        }

        num1 = numActual.toDoubleOrNull()
        if (num1 == null) {
            tvResultado.text = "Error"
            return
        }
        signo = (view as TextView).text.toString()
        tvResultado.append(signo)
        numActual = ""
    }

    fun onCClick(view: View){
        limpiar()
    }

    fun limpiar(){
        num1 = null
        num2 = null
        signo = null
        numActual = ""
        tvResultado.text = "0"
        resultadoEnPantalla = false
    }

    fun onIgualClick(view: View) {
        if (resultadoEnPantalla){
            limpiar()
            return
        }

        num2 = numActual.toDoubleOrNull()
        if (num1 == null || num2 == null) {
            tvResultado.text = "Error"
            return
        }
        val result = when (signo) {
            "+" -> num1!! + num2!!
            "-" -> num1!! - num2!!
            "*" -> num1!! * num2!!
            "/" -> {
                if (num2 == 0.0) {
                    "Error"
                } else {
                    num1!! / num2!!
                }
            }
            else -> 0.0
        }
        tvResultado.text = result.toString()
        resultadoEnPantalla = true
    }
}
