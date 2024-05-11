package com.example.wazitoecommerce.ui.theme.screens.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.wazitoecommerce.data.ProductViewModel
import com.example.wazitoecommerce.models.Product
import com.example.wazitoecommerce.ui.theme.WazitoECommerceTheme

@Composable
fun ViewProductsScreen(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var productRepository = ProductViewModel(navController, context)


        val emptyProductState = remember { mutableStateOf(Product("","","","","")) }
        var emptyProductsListState = remember { mutableStateListOf<Product>() }

        var products = productRepository.allProducts(emptyProductState, emptyProductsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All entries",
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                color = Color.Black)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(Modifier.padding(start = 20.dp)){
                items(products){
                    ProductItem(
                        name = it.name,
                        quantity = it.quantity,
                        price = it.price,
                        id = it.id,
                        navController = navController,
                        productRepository = productRepository,
                        productImage = it.imageUrl
                    )
                }
            }
        }
    }
}


@Composable
fun ProductItem(name:String, quantity:String, price:String, id:String,
                navController:NavHostController,
                productRepository:ProductViewModel, productImage:String) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Row {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(productImage),
                    contentDescription = null,
                    modifier = Modifier.size(height = 150.dp, width = 100.dp)
                )
            }

            Spacer(modifier = Modifier.width(30.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ){
                Text(text = name,
                    textAlign = TextAlign.Right,
                    fontStyle = FontStyle.Italic,
                    fontSize = 15.sp
                )
                Text(text = quantity,
                    textAlign = TextAlign.Justify,
                    fontSize = 20.sp
                    )
                Text(text = price,
                    textAlign = TextAlign.Right,
                    fontSize = 15.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row {
            Button(onClick = {
                productRepository.deleteProduct(id)
            }
                ) {
                Text(text = "Delete")
            }


        }

        Divider(color = Color.Black, modifier = Modifier.fillMaxWidth().width(3.dp))


    }
}

@Composable
@Preview(showBackground = true)
fun ViewProductsScreenPreview(){
    WazitoECommerceTheme {
        ViewProductsScreen(navController = rememberNavController())
    }
}