package com.example.wazitoecommerce.ui.theme.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wazitoecommerce.R
import com.example.wazitoecommerce.navigation.ADD_ENTRY_URL
import com.example.wazitoecommerce.navigation.ADD_HOUSES_URL
import com.example.wazitoecommerce.navigation.ADD_PRODUCTS_URL
import com.example.wazitoecommerce.navigation.VIEW_ENTRIES_URL
import com.example.wazitoecommerce.navigation.VIEW_HOUSES_URL
import com.example.wazitoecommerce.navigation.VIEW_PRODUCTS_URL
import com.example.wazitoecommerce.ui.theme.WazitoECommerceTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController:NavHostController,
               title: String,
               titleFontSize: TextUnit = MaterialTheme.typography.titleLarge.fontSize,
               titleFontWeight: FontWeight = FontWeight.Bold,
               description: String,
               descriptionFontSize: TextUnit = MaterialTheme.typography.titleLarge.fontSize,
               descriptionFontWeight: FontWeight = FontWeight.Normal,
               descriptionMaxLines: Int = 10,
               padding: Dp = 12.dp){
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        var selected by remember { mutableIntStateOf(0) }
        Scaffold(
            bottomBar = {
                NavigationBar {
                    bottomNavItems.forEachIndexed { index, bottomNavItem ->
                        NavigationBarItem(
                            selected = index == selected,
                            onClick = {
                                selected = index
                                navController.navigate(bottomNavItem.route)
                            },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (bottomNavItem.badges != 0) {
                                            Badge {
                                                Text(text = bottomNavItem.badges.toString())
                                            }
                                        } else if (bottomNavItem.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(imageVector =
                                    if (index == selected)
                                        bottomNavItem.selectedIcon
                                    else
                                        bottomNavItem.unselectedIcon,
                                        contentDescription = bottomNavItem.title)
                                }

                            },
                            label = {
                                Text(text = bottomNavItem.title)
                            })
                    }
                }
            },


            floatingActionButton = {
                FloatingActionButton(onClick = {
                    navController.navigate(ADD_PRODUCTS_URL)
                }) {
                    IconButton(onClick = {
                        navController.navigate(ADD_PRODUCTS_URL)
                    }) {
                        Icon(imageVector = Icons.Default.Add,
                            contentDescription = "menu")
                    }
                }
            },
            //Content Section
            content = @Composable{
                Column(
                    modifier = Modifier.fillMaxSize(),

                ){
                    //----TopAppBar----
                    TopAppBar(title = { Text(text = "Lifestyle",
                        color = Color.White) },
                        colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Black),
                        navigationIcon = {},
                        actions = {

                        }
                    )
                    //---END OF TOPAPPBAR---

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Image(
                            painter = painterResource(id = R.drawable.texture),
                            contentDescription = "Downtown",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = "",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black,
                            textAlign = TextAlign.Center)

                    }


                    //End of Box


                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Lifestyle",
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                    Spacer(modifier = Modifier.height(30.dp))

                    var expandedState by remember { mutableStateOf(false) }
                    val rotationState by animateFloatAsState(
                        targetValue = if (expandedState) 180f else 0f
                    )

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = LinearOutSlowInEasing
                                )
                            ),
                        shape = AlertDialogDefaults.shape,
                        onClick = {
                            expandedState = !expandedState
                        },
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 7.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(padding)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier
                                        .weight(6f),
                                    text = title,
                                    fontSize = titleFontSize,
                                    fontWeight = titleFontWeight,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                IconButton(
                                    modifier = Modifier
                                        .weight(1f)
                                        .alpha(0.2f)
                                        .rotate(rotationState),
                                    onClick = {
                                        expandedState = !expandedState
                                    }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        tint = Color.Black,
                                        contentDescription = "Drop-Down Arrow"
                                    )
                                }
                            }
                            if (expandedState) {
                                Text(
                                    text = description,
                                    fontSize = descriptionFontSize,
                                    fontWeight = descriptionFontWeight,
                                    maxLines = descriptionMaxLines,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }

                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Image(
                            painter = painterResource(id = R.drawable.texture),
                            contentDescription = "Downtown",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = "CHRONICLES",
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.White,
                            textAlign = TextAlign.Center)
                    }


                    //End of Box


                }



            }

        )

    }
}



val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route="home",
        selectedIcon=Icons.Filled.Home,
        unselectedIcon=Icons.Outlined.Home,
        hasNews = false,
        badges=0
    ),



    BottomNavItem(
        title = "Entries",
        route="view_products",
        selectedIcon=Icons.Filled.Search,
        unselectedIcon=Icons.Outlined.Search,
        hasNews = true,
        badges=5
    ),

    BottomNavItem(
        title = "Signout",
        route="login",
        selectedIcon=Icons.Filled.Face,
        unselectedIcon=Icons.Filled.ExitToApp,
        hasNews = true,
        badges=1
    ),


    )



data class BottomNavItem(
    val title :String,
    val route :String,
    val selectedIcon: ImageVector,
    val unselectedIcon :ImageVector,
    val hasNews :Boolean,
    val badges :Int
)



@Composable
@Preview(showBackground = true)
fun HomeScreenPreview(){
    WazitoECommerceTheme {
        HomeScreen(navController = rememberNavController(), title = "The Lifestyle Chronicles" , description = "Daily journal for daily events happening in life. A digital and safer way to update private memories.")
    }
}