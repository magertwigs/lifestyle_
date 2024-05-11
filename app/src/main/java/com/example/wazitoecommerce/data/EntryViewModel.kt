package com.example.wazitoecommerce.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.wazitoecommerce.models.Entry
import com.example.wazitoecommerce.navigation.LOGIN_URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class EntryViewModel(var navController:NavHostController, var context: Context) {
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

    fun uploadEntry(date:String, thoughts:String, id:String, filePath:Uri){
        val entryId = System.currentTimeMillis().toString()
        val storageRef = FirebaseStorage.getInstance().getReference()
                                .child("Entries/$entryId")
        progress.show()
        storageRef.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Save data to db
                storageRef.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var entry = Entry(date,thoughts,imageUrl,entryId)
                    var databaseRef = FirebaseDatabase.getInstance().getReference()
                        .child("Entriess/$entryId")
                    databaseRef.setValue(entry).addOnCompleteListener {
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

    fun allEntries(
        entry:MutableState<Entry>,
        entries:SnapshotStateList<Entry>):SnapshotStateList<Entry>{
        progress.show()
        var ref = FirebaseDatabase.getInstance().getReference()
                    .child("Entries")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                entries.clear()
                for (snap in snapshot.children){
                    var retrievedEntry = snap.getValue(Entry::class.java)
                    entry.value = retrievedEntry!!
                    entries.add(retrievedEntry)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
            }
        })
        return entries
    }

    fun deleteEntry(entryId:String){
        var ref = FirebaseDatabase.getInstance().getReference()
                            .child("Entries/$entryId")
        ref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}