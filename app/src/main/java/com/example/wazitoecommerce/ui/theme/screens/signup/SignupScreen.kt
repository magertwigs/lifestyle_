package com.example.wazitoecommerce.ui.theme.screens.signup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wazitoecommerce.R
import com.example.wazitoecommerce.data.AuthViewModel
import com.example.wazitoecommerce.navigation.LOGIN_URL
import com.example.wazitoecommerce.ui.theme.SomeBlue
import com.example.wazitoecommerce.ui.theme.SomeBlue2
import com.example.wazitoecommerce.ui.theme.WazitoECommerceTheme
import com.google.firebase.annotations.concurrent.Background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(navController:NavHostController){

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
            ) {

            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "signup",
                modifier = Modifier
                    .size(80.dp),
                contentScale = ContentScale.Crop,
            )

            Text(
                text = "Signup ",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = Modifier.height(30.dp))

            var name by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            OutlinedTextField(
                modifier = Modifier
                    .border(
                        BorderStroke(
                            width = 2.dp,
                            color = Color.White
                        )
                    )
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(Color.White, Color.LightGray)
                        )
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                value = name,
                onValueChange = {name = it},
                label = { Text(text = "Enter name")},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                modifier = Modifier
                    .border(
                        BorderStroke(
                            width = 2.dp,
                            color = Color.White
                        )
                    )
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(Color.White, Color.LightGray)
                        )
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                value = email,
                onValueChange = {email = it},
                label = { Text(text = "Enter email")},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                modifier = Modifier
                    .border(
                        BorderStroke(
                            width = 2.dp,
                            color = Color.White
                        )
                    )
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(Color.White, Color.LightGray)
                        )
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                value = password,
                onValueChange = {password = it},
                label = { Text(text = "Enter password")},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            val context = LocalContext.current
            val authViewModel = AuthViewModel(navController, context)

            Row {
                OutlinedButton(onClick = {
                    authViewModel.signup(name, email, password)
                }) {
                    Text(text = "Register")
                }

                Spacer(modifier = Modifier.width(30.dp))

                OutlinedButton(onClick = {
                    navController.navigate(LOGIN_URL)
                }) {
                    Text(text = "Login instead")
                }
            }


        }

    }


}

@Composable
@Preview(showBackground = true)
fun SignupScreenPreview(){
    WazitoECommerceTheme {
        SignupScreen(navController = rememberNavController())
    }
}