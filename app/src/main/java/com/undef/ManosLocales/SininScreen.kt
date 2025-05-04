package com.undef.ManosLocales

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Sinin() {
    val userName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordChecker = remember { mutableStateOf("") }
    val termsConditions = remember { mutableStateOf(false) }
    val dateOfBirth = remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFe3d6c3)),
        contentAlignment = Alignment.Center
    )  {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(0.85f)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text("Registrate", color = Color(0xFF404934), fontSize = 30.sp)
            }

            TextField(
                value = userName.value,
                onValueChange = { userName.value = it },
                label = { Text("Nombre de usuario", color = Color(0xFF7C5C44))},
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color(0xFF7C5C44),
                    unfocusedTextColor = Color(0xFF7C5C44),
                    focusedLabelColor = Color(0xFF7C5C44),
                    unfocusedLabelColor = Color(0xFF7C5C44),
                    cursorColor = Color(0xFF7C5C44),
                    focusedIndicatorColor = Color(0xFF7C5C44),
                    unfocusedIndicatorColor = Color(0xFF7C5C44)
                )
            )

            TextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email", color = Color(0xFF7C5C44))},
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color(0xFF7C5C44),
                    unfocusedTextColor = Color(0xFF7C5C44),
                    focusedLabelColor = Color(0xFF7C5C44),
                    unfocusedLabelColor = Color(0xFF7C5C44),
                    cursorColor = Color(0xFF7C5C44),
                    focusedIndicatorColor = Color(0xFF7C5C44),
                    unfocusedIndicatorColor = Color(0xFF7C5C44)
                )
            )

            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Contraseña", color = Color(0xFF7C5C44)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color(0xFF7C5C44),
                    unfocusedTextColor = Color(0xFF7C5C44),
                    focusedLabelColor = Color(0xFF7C5C44),
                    unfocusedLabelColor = Color(0xFF7C5C44),
                    cursorColor = Color(0xFF7C5C44),
                    focusedIndicatorColor = Color(0xFF7C5C44),
                    unfocusedIndicatorColor = Color(0xFF7C5C44)
                )
            )
            TextField(
                value = passwordChecker.value,
                onValueChange = { passwordChecker.value = it },
                label = { Text("Repita la contraseña", color = Color(0xFF7C5C44)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color(0xFF7C5C44),
                    unfocusedTextColor = Color(0xFF7C5C44),
                    focusedLabelColor = Color(0xFF7C5C44),
                    unfocusedLabelColor = Color(0xFF7C5C44),
                    cursorColor = Color(0xFF7C5C44),
                    focusedIndicatorColor = Color(0xFF7C5C44),
                    unfocusedIndicatorColor = Color(0xFF7C5C44)
                )
            )

            TextField(
                value = dateOfBirth.value,
                onValueChange = { dateOfBirth.value = it },
                label = { Text("Nacimiento", color = Color(0xFF7C5C44))},
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color(0xFF7C5C44),
                    unfocusedTextColor = Color(0xFF7C5C44),
                    focusedLabelColor = Color(0xFF7C5C44),
                    unfocusedLabelColor = Color(0xFF7C5C44),
                    cursorColor = Color(0xFF7C5C44),
                    focusedIndicatorColor = Color(0xFF7C5C44),
                    unfocusedIndicatorColor = Color(0xFF7C5C44)
                )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = termsConditions.value,
                    onCheckedChange = { termsConditions.value = it },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Color.White,
                        uncheckedColor = Color(0xFF7C5C44),
                        checkedColor = Color(0xFF7C5C44)
                    )
                )
                Text("Aceptar \"Terminos y condiciones\".", color = Color(0xFF7C5C44))

            }

            Button(
                onClick = {
                    val intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF997052)
                ),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text("Registrarse", color = Color.White)
            }
            Text(
                text = "Ya tengo una cuenta.",
                color = Color(0xFF404934),
                modifier = Modifier
                    .clickable {
                        val intent = Intent(context, LoginActivity::class.java)
                        context.startActivity(intent)
                    }
            )
        }
    }
}


