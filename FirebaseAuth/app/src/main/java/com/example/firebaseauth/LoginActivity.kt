package com.example.firebaseauth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseauth.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.login.setOnClickListener(fun(it: View) {
            val userEmail = binding.username.text.toString()
            val password = binding.password.text.toString()
            auth.signInWithEmailAndPassword(userEmail, password)
                .addOnCompleteListener(this, fun(it: Task<AuthResult>) {
                    if (it.isSuccessful) {
                        startActivity(
                            Intent(this, StorageActivity::class.java)
                        )
                        finish()
                    } else {
                        Log.w("LoginActivity", "signInWithEmail", it.exception);
                        Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                })
        })

        binding.signUp.setOnClickListener(fun(it: View) {
            val userEmail = binding.username.text.toString()
            val password = binding.password.text.toString()

            auth.createUserWithEmailAndPassword(userEmail,password)
                .addOnCompleteListener(this,fun(it: Task<AuthResult>){
                    if (it.isSuccessful) {
                        startActivity(
                            Intent(this, StorageActivity::class.java)
                        )
                        finish()
                    } else {
                        Log.w("LoginActivity", "signInWithEmail", it.exception);
                        Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                })

        })
    }
}