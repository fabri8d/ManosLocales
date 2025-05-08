package com.undef.ManosLocales
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.KeyboardType
import com.undef.ManosLocales.rememberpw.RememberPWActivity
import com.undef.ManosLocales.utils.textFieldColors


@Composable
fun Login() {
    var email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val rememberMe = remember { mutableStateOf(false) }
    val context = LocalContext.current


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
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.logotipo),
                    contentDescription = "Logo de la app",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(300.dp)
                )
                Text("Bienvenido", color = Color(0xFF404934), fontSize = 40.sp)
            }

            Text("Inicie sesion para continuar",
                color = Color(0xFF404934),
                fontSize = 15.sp,
                modifier = Modifier.padding(top = 10.dp))
            TextField(
                value = email.value,
                onValueChange = { email.value = it },
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                label = { Text("Email", color = Color(0xFF7C5C44)) },
                singleLine = true,
                colors = textFieldColors()
            )

            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Contraseña", color = Color(0xFF7C5C44)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = textFieldColors()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ){
                Text(
                    text = "Olvide mi contraseña",
                    color = Color(0xFF404934),
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(context, RememberPWActivity::class.java)
                            context.startActivity(intent)
                        }

                )
            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = rememberMe.value,
                    onCheckedChange = { rememberMe.value = it },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Color.White,
                        uncheckedColor = Color(0xFF7C5C44),
                        checkedColor = Color(0xFF7C5C44)
                    )
                )
                Text("Recordame", color = Color(0xFF7C5C44))

            }

            Button(
                onClick = {
                    if (email.value.contains("@")) {
                        val intent = Intent(context, MainMenuActivity::class.java)
                        context.startActivity(intent)
                    } else {
                        Toast.makeText(context, "Introduce un email válido", Toast.LENGTH_SHORT).show()
                    }  },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF997052)
                ),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text("Iniciar sesion",
                    color = Color.White
                )
            }
            Text(
                text = "¿No tenés cuenta? Registrate.",
                color = Color(0xFF404934),
                modifier = Modifier
                    .clickable {
                        val intent = Intent(context, SinginActivity::class.java)
                        context.startActivity(intent)
                    }
            )

        }
    }
}

