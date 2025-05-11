package com.undef.ManosLocales.authenticator

import android.app.DatePickerDialog
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.undef.ManosLocales.utils.textFieldColors
import java.util.Calendar

@Composable
fun Sinin() {
    val name = remember { mutableStateOf("") }
    val surname = remember { mutableStateOf("") }
    val userName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordChecker = remember { mutableStateOf("") }
    val termsConditions = remember { mutableStateOf(false) }
    val dateOfBirth = remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
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

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFe3d6c3)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(0.85f)
        ) {

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text("Registrate", color = Color(0xFF404934), fontSize = 30.sp)
            }

            Row (
                modifier = Modifier.fillMaxWidth()
            ){
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


            TextField(
                value = userName.value,
                onValueChange = { userName.value = it },
                label = { Text("Nombre de usuario", color = Color(0xFF7C5C44)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = textFieldColors()
            )

            TextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email", color = Color(0xFF7C5C44)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = textFieldColors()
            )

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
                    if (password.value.length >=8 && passwordChecker.value == password.value && termsConditions.value && email.value.contains("@")) {
                        val intent = Intent(context, LoginActivity::class.java)
                        context.startActivity(intent)
                    }else if (!email.value.contains("@")){
                        Toast.makeText(context, "Introduce un email válido", Toast.LENGTH_SHORT).show()
                    }else if (password.value.length < 8){
                        Toast.makeText(context, "La contraseña es menor a 8 caracteres", Toast.LENGTH_SHORT).show()
                    }else if (passwordChecker.value != password.value){
                        Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                    }else if (!termsConditions.value){
                        Toast.makeText(context, "Debe aceptar los terminos y condiciones", Toast.LENGTH_SHORT).show()
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
}

