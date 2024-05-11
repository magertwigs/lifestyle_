package com.example.wazitoecommerce.ui.theme.screens.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wazitoecommerce.navigation.LOGIN_URL
import com.example.wazitoecommerce.ui.theme.WazitoECommerceTheme
import com.example.wazitoecommerce.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    )
    {

        val coroutine = rememberCoroutineScope()
        coroutine.launch {
            delay(2000)
            navController.navigate(LOGIN_URL)
        }

        Image(
            painter = painterResource(id = R.drawable.texture),
            contentDescription = "Downtown",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column {

            Text(
                text = "Lifestyle",
                fontSize = 50.sp,
                fontWeight = FontWeight.Thin,
                fontFamily = FontFamily.Monospace,
                color = Color.White,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(start = 20.dp,top = 30.dp))

            Text(
                text = "CHRONICLES",
                fontSize = 60.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Thin,
                color = Color.White,
                textAlign = TextAlign.Right,
                modifier = Modifier.padding(start = 20.dp,top = 600.dp))
        }
    }


    //End of Box

}
@Composable
@Preview(showBackground = true)
fun SplashScreenPreview(){
    WazitoECommerceTheme {
        SplashScreen(navController = rememberNavController())
    }
}