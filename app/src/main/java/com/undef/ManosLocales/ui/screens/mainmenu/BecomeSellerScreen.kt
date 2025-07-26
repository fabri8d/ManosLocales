package com.undef.ManosLocales.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.undef.ManosLocales.ui.viewmodels.SellerViewModel
import com.undef.ManosLocales.ui.viewmodels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BecomeSellerScreen(
    userViewModel: UserViewModel = hiltViewModel(),
    sellerViewModel: SellerViewModel = hiltViewModel(),
    onSellerCreated: () -> Unit,
    onCancel: () -> Unit,
) {
    val context = LocalContext.current
    var businessName by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val currentUser by userViewModel.currentUser.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear Tienda") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF404934))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFe3d6c3)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = businessName,
                onValueChange = { businessName = it },
                label = { Text("Nombre del local") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(16.dp))

            val user = currentUser
            Button(
                onClick = {
                    if (businessName.isBlank()) {
                        Toast.makeText(context, "Ingrese un nombre válido", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    if (user == null) {
                        Toast.makeText(context, "Usuario no autenticado", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    isLoading = true
                    sellerViewModel.registerSeller(
                        userId = user.id,
                        businessName = businessName,
                        onSuccess = {
                            isLoading = false
                            Toast.makeText(context, "Vendedor creado con éxito", Toast.LENGTH_SHORT).show()
                            onSellerCreated()
                        },
                        onError = { errorMessage ->
                            isLoading = false
                            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    )
                },
                enabled = !isLoading
            ) {
                Text("Aceptar")
            }

            Spacer(modifier = Modifier.height(8.dp))
            TextButton(onClick = onCancel) {
                Text("Cancelar")
            }
        }
    }
}
