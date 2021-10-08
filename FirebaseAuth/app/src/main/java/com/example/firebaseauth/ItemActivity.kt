package com.example.firebaseauth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseauth.databinding.ActivityItemBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore


class ItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemBinding
    private val db: FirebaseFirestore = Firebase.firestore
    private val itemsCollectionRef = db.collection("items")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.intent = getIntent()
        val id = intent.getStringExtra("id").toString()

        itemsCollectionRef.document(id).get().addOnSuccessListener {
            binding.detailName.text = "Name : " + it["name"].toString()
            binding.detailPrice.text = "Price : " + it["price"].toString()
            if(!(it["cart"] as Boolean)){
                binding.button2.text = "Add to Cart"
            }
            else{
                binding.button2.text = "Remove from Cart"
            }
        }.addOnFailureListener{
        }

        binding.button2.setOnClickListener {
            itemsCollectionRef.document(id).get().addOnSuccessListener {
                if(!(it["cart"] as Boolean)) {
                    itemsCollectionRef.document(id).update("cart",true)
                    binding.button2.text = "Remove from Cart"
                }
                else{
                    itemsCollectionRef.document(id).update("cart",false)
                    binding.button2.text = "Add to Cart"
                }
            }
        }
    }
}