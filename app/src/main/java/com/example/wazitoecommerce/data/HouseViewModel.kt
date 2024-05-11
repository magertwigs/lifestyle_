package com.example.wazitoecommerce.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.wazitoecommerce.models.House
import com.example.wazitoecommerce.models.Product
import com.example.wazitoecommerce.navigation.LOGIN_URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class HouseViewModel(var navController:NavHostController, var context: Context) {
    var authViewModel:AuthViewModel
    var progress:ProgressDialog
    init {
        authViewModel = AuthViewModel(navController, context)
        if (!authViewModel.isLoggedIn()){
            navController.navigate(LOGIN_URL)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }

    fun uploadHouse(owner:String, location:String, price:String, filePath:Uri){
        val houseId = System.currentTimeMillis().toString()
        val storageRef = FirebaseStorage.getInstance().getReference()
                                .child("House/$houseId")
        progress.show()
        storageRef.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Save data to db
                storageRef.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    Product(owner,location,price,imageUrl,houseId)
                    var databaseRef = FirebaseDatabase.getInstance().getReference()
                        .child("House/$houseId")
                    databaseRef.setValue(houseId).addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }else{
                Toast.makeText(this.context, "Upload error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun allHouses(
        house:MutableState<House>,
        houses:SnapshotStateList<House>):SnapshotStateList<House>{
        progress.show()
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("Houses")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                houses.clear()
                for (snap in snapshot.children){
                    var retrievedHouse = snap.getValue(House::class.java)
                    house.value = retrievedHouse!!
                    houses.add(retrievedHouse)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
            }
        })
        return houses
    }

    fun deleteHouse(houseId:String){
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("Houses/$houseId")
        ref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}