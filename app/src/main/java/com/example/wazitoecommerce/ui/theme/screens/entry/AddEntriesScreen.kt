package com.example.wazitoecommerce.ui.theme.screens.entry

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wazitoecommerce.data.EntryViewModel
import com.example.wazitoecommerce.data.ProductViewModel
import com.example.wazitoecommerce.ui.theme.WazitoECommerceTheme
import com.example.wazitoecommerce.ui.theme.screens.house.ImagePicker
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEntriesScreen(navController:NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add Entries",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )

        var entryDate by remember { mutableStateOf("") }
        var thought by remember { mutableStateOf("") }
        var id by remember { mutableStateOf("") }
        val context = LocalContext.current

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = entryDate,
            onValueChange = { entryDate = it },
            Modifier.size(width = 80.dp,height = 60.dp),
            label = { Text(text = "Date *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = thought,
            onValueChange = { thought = it },
            Modifier.size(width = 250.dp,height = 150.dp)
                .border(BorderStroke(width = 2.dp, Color.Black)),
            label = { Text(text = "(Thought *)") },
            trailingIcon = { Icon(imageVector = Icons.Default.Create, contentDescription = "create") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            Modifier.size(width = 120.dp,height = 60.dp),
            label = { Text(text = "(name)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))



        //---------------------IMAGE PICKER START-----------------------------------//

        var modifier = Modifier
        ImagePicker(modifier,context, navController, entryDate.trim(), thought.trim(), id.trim())

        //---------------------IMAGE PICKER END-----------------------------------//



    }
}

@Composable
fun ImagePicker1(modifier: Modifier = Modifier, context: Context,navController: NavHostController, entryDate:String, thought:String, id:String) {
    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    Column(modifier = modifier
        .verticalScroll(rememberScrollState())
    ) {
        if (hasImage && imageUri != null) {
            val bitmap = MediaStore.Images.Media.
            getBitmap(context.contentResolver,imageUri)
            Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Selected image")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,) {
            Button(
                onClick = {
                    imagePicker.launch("image/*")
                },
            ) {
                Text(
                    text = "Select Image"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                //-----------WRITE THE UPLOAD LOGIC HERE---------------//
                var entryRepository = EntryViewModel(navController,context)
                entryRepository.uploadEntry(entryDate, thought, id,imageUri!!)


            }) {
                Text(text = "Upload")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AddEntriesScreenPreview(){
    WazitoECommerceTheme {
        AddEntriesScreen(navController = rememberNavController())
    }
}