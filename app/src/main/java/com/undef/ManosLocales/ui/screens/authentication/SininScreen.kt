package com.undef.ManosLocales.ui.screens.authentication

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.undef.ManosLocales.data.local.entities.User
import com.undef.ManosLocales.utils.textFieldColors
import java.util.Calendar
import android.app.DatePickerDialog
import com.undef.ManosLocales.data.remote.models.UserRegisterRequest
import com.undef.ManosLocales.ui.viewmodels.UserViewModel

@Composable
fun Sinin(
    userViewModel: UserViewModel = hiltViewModel()
) {
    val name = remember { mutableStateOf("") }
    val surname = remember { mutableStateOf("") }
    val userName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordChecker = remember { mutableStateOf("") }
    val termsConditions = remember { mutableStateOf(false) }
    val dateOfBirth = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year)
                dateOfBirth.value = selectedDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.maxDate = System.currentTimeMillis()
        }
    }
    val registeredUser by userViewModel.registeredUser.collectAsState()

    LaunchedEffect(registeredUser) {
        registeredUser?.let { user ->
            Toast.makeText(context, "Registrado: ${user.email}", Toast.LENGTH_LONG).show()

            // Navegar a LoginActivity después del registro exitoso
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    val emailAlreadyUsed by userViewModel.emailAlreadyUsed.collectAsState()

    LaunchedEffect(emailAlreadyUsed) {
        if (emailAlreadyUsed) {
            Toast.makeText(context, "Este correo ya está registrado", Toast.LENGTH_LONG).show()
            userViewModel.resetEmailAlreadyUsedFlag()

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFe3d6c3))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Registrate", color = Color(0xFF404934), fontSize = 30.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Nombre", color = Color(0xFF7C5C44)) },
                singleLine = true,
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = textFieldColors()
            )
            Spacer(modifier = Modifier.width(10.dp))
            TextField(
                value = surname.value,
                onValueChange = { surname.value = it },
                label = { Text("Apellido", color = Color(0xFF7C5C44)) },
                singleLine = true,
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = textFieldColors()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = userName.value,
            onValueChange = { userName.value = it },
            label = { Text("Nombre de usuario", color = Color(0xFF7C5C44)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = textFieldColors()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email", color = Color(0xFF7C5C44)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = textFieldColors()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Contraseña", color = Color(0xFF7C5C44)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = textFieldColors()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = passwordChecker.value,
            onValueChange = { passwordChecker.value = it },
            label = { Text("Repita la contraseña", color = Color(0xFF7C5C44)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = textFieldColors()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = dateOfBirth.value,
            onValueChange = {},
            label = { Text("Nacimiento", color = Color(0xFF7C5C44)) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { datePickerDialog.show() },
            enabled = false,
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color(0xFFe3d6c3),
                disabledTextColor = Color(0xFF7C5C44),
                disabledLabelColor = Color(0xFF7C5C44),
                disabledIndicatorColor = Color(0xFF7C5C44)
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

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

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Validaciones
                when {
                    name.value.isBlank() || surname.value.isBlank() || userName.value.isBlank() || email.value.isBlank() || password.value.isBlank() -> {
                        Toast.makeText(context, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                    }
                    !email.value.contains("@") -> {
                        Toast.makeText(context, "Introduce un email válido", Toast.LENGTH_SHORT).show()
                    }
                    password.value.length < 8 -> {
                        Toast.makeText(context, "La contraseña es menor a 8 caracteres", Toast.LENGTH_SHORT).show()
                    }
                    passwordChecker.value != password.value -> {
                        Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                    }
                    !termsConditions.value -> {
                        Toast.makeText(context, "Debe aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        // Crear el usuario
                        /*val newUser = User(
                            id = 0, // o lo que uses para que se genere automáticamente
                            username = userName.value.trim(),
                            email = email.value.trim(),
                            password = password.value,
                            city = "",
                            dateOfBirth = dateOfBirth.value.toString(),
                            name = name.value,
                            image = 1,
                            surname = surname.value,
                            favoriteProducts = null
                        )
                        userViewModel.register(newUser)*/
                        val request = UserRegisterRequest(
                            username = userName.value.trim(),
                            firstName = name.value,
                            lastName = surname.value,
                            email = email.value.trim(),
                            password = password.value
                        )
                        userViewModel.registerUserRemote(request)

                    }
                }
            },
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

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Ya tengo una cuenta.",
            color = Color(0xFF404934),
            modifier = Modifier.clickable {
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
            }
        )
    }
}
