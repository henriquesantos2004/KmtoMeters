package com.example.kmtometer

// Importações necessárias para o Jetpack Compose funcionar corretamente
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmtometer.ui.theme.KmtoMeterTheme

// A classe principal da app — é o ponto de entrada
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aqui define-se o conteúdo da app com Jetpack Compose
        setContent {
            KmtoMeterTheme { // Aplica o tema definido no projeto
                Surface(
                    modifier = Modifier.fillMaxSize(), // Ocupar ecra tudo
                    color = Color(0xFF121B3A) // Define o fundo azul escuro
                ) {
                    EcraConversor() // Chama a função que desenha o ecrã principal
                }
            }
        }
    }
}

// Esta função desenha o ecrã do conversor
@Composable
fun EcraConversor() {
    // "input" guarda o valor que o utilizador escreve
    var input by remember { mutableStateOf("") }

    // "resultado" guarda o texto convertido (metros ou km)
    var resultado by remember { mutableStateOf("") }

    // Box serve para alinhar tudo ao centro do ecrã
    Box(
        modifier = Modifier
            .fillMaxSize() // Ocupar comprimento total
            .background(Color(0xFF121B3A)), // Fundo azul escuro
        contentAlignment = Alignment.Center
    ) {
        // Coluna vertical para empilhar elementos
        Column(
            modifier = Modifier
                .padding(24.dp) // Espaçamento nas margens
                .fillMaxWidth(), // Ocupar largura total
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp) // Espaço entre elementos
        ) {
            // Título da app
            Text(
                "Conversor de Distâncias",
                fontSize = 26.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            // Caixa de texto onde o utilizador escreve o valor
            OutlinedTextField(
                value = input,
                onValueChange = { input = it }, // Atualiza o input ao escrever
                label = { Text("Insere um valor", color = Color.White) }, // Rótulo
                textStyle = LocalTextStyle.current.copy(color = Color.White), // Texto branco
                modifier = Modifier.fillMaxWidth(), // Caixa ocupa largura total
                colors = OutlinedTextFieldDefaults.colors( // Estilo da caixa
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.LightGray,
                    cursorColor = Color.White
                )
            )

            // Linha horizontal com os dois botões lado a lado
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp) // Espaço entre botões
            ) {
                // Botão para converter Km → Metros
                ElevatedButton(
                    onClick = {
                        val km = input.toDoubleOrNull() // Tenta converter o input para número
                        resultado = if (km != null) "${km * 1000} metros" else "Valor inválido"
                    },
                    modifier = Modifier
                        .weight(1f) // Ocupar metade da linha
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp) // Cantos arredondados
                ) {
                    Text("Km → Metros")
                }

                // Botão para converter Metros → Km
                ElevatedButton(
                    onClick = {
                        val metros = input.toDoubleOrNull()
                        resultado = if (metros != null) "${metros / 1000} quilómetros" else "Valor inválido"
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Metros → Km")
                }
            }

            // Cartão que mostra o resultado da conversão
            if (resultado.isNotBlank()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E2A50)) // Cor de fundo do cartão
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = resultado, // Mostra o valor convertido
                            fontSize = 18.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}