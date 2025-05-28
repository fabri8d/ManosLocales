package com.undef.ManosLocales.ui.screens.authentication.rememberpw

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.undef.ManosLocales.utils.textFieldColors

@Composable
fun ResetPassword(onFinished: () -> Unit){
    val context = LocalContext.current
    var password = remember { mutableStateOf("") }
    var passwordChecker = remember { mutableStateOf("") }
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
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text("¡Ya casi terminamos!",
                    color = Color(0xFF404934),
                    fontSize = 40.sp)
                Text("Crea una nueva contraseña",
                    color = Color(0xFF404934),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 10.dp))
            }


            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Nueva contraseña", color = Color(0xFF7C5C44)) },
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


            Button(
                onClick = {
                    if (password.value.length >=8 && passwordChecker.value.equals(password.value)) {
                        onFinished()
                    } else if (password.value.length < 8){
                        Toast.makeText(context, "La contraseña es menor a 8 caracteres", Toast.LENGTH_SHORT).show()
                    }else if (!passwordChecker.value.equals(password.value)){
                        Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
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
                Text("Cambiar contraseña",
                    color = Color.White
                )
            }
        }
    }
}