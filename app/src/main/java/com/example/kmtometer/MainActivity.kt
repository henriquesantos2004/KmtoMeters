package com.example.kmtometer

// Importações necessárias para o Jetpack Compose funcionar corretamente
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import com.example.kmtometer.ui.theme.KmtoMeterTheme
// A classe principal da app — é o ponto de entrada
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aqui define-se o conteúdo da app com Jetpack Compose
        setContent {
            KmtoMeterTheme { // Aplica o tema definido no projeto
                Surface(
                    modifier = Modifier.fillMaxSize(), // Ocupar todo o ecrã
                    color = Color(0xFF121B3A) // Define o fundo azul escuro
                ) {
                    ecraConversor() // Chama a função que desenha o ecrã principal
                }
            }
        }
    }
}

fun ecraConversor() {
}