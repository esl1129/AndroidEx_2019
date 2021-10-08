package com.example.firebaseauth

import androidx.appcompat.app.AppCompatActivity
import android.Manifest
import android.content.ContentUris
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.example.firebaseauth.databinding.ActivityStorageBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage


class StorageActivity  : AppCompatActivity(){
    lateinit var storage: FirebaseStorage
    lateinit var binding: ActivityStorageBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Firebase.auth.currentUser ?: finish()

        storage =Firebase.storage

        val storageRef = storage.reference
        val remoteConfig = Firebase.remoteConfig
        val configSetting = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 1
        }
        remoteConfig.setConfigSettingsAsync(configSetting)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig.fetchAndActivate().addOnCompleteListener(this){
            val fileName1 = remoteConfig.getString("season")
            val imageRef1 = storageRef.child("images/${fileName1}.jpg")
            displayImageRef(imageRef1, binding.imageView)
        }

        binding.button.setOnClickListener(fun(it: View){
            remoteConfig.fetchAndActivate().addOnCompleteListener(this){
                val fileName2 = remoteConfig.getString("season")
                val imageRef2 = storageRef.child("images/${fileName2}.jpg")
                displayImageRef(imageRef2, binding.imageView)
            }
        })
    }


    private fun displayImageRef(imageRef: StorageReference?, view: ImageView){
        imageRef?.getBytes(Long.MAX_VALUE)?.addOnSuccessListener {
            val bmp = BitmapFactory.decodeByteArray(it,0,it.size)
            view.setImageBitmap(bmp)
        }?.addOnFailureListener{

        }
    }
}