package com.example.firebaseauth

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseauth.databinding.ItemBinding
import com.google.firebase.firestore.QueryDocumentSnapshot

data class Item(val id: String, val name: String, val price: Int, val cart: Boolean) {
    constructor(doc: QueryDocumentSnapshot) :
            this(doc.id, doc["name"].toString(), doc["price"].toString().toIntOrNull() ?: 0, (doc["cart"] ?: false) as Boolean)
    constructor(key: String, map: Map<*, *>) :
            this(key, map["name"].toString(), map["price"].toString().toIntOrNull() ?: 0, (map["cart"] ?: false) as Boolean)
}

class MyViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

class MyAdapter(private val context: Context, private var items: List<Item>)
    : RecyclerView.Adapter<MyViewHolder>() {

    fun interface OnItemClickListener {
        fun onItemClick(id: String)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    fun updateList(newList: List<Item>) {
        items = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemBinding = ItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.binding.itemid.text = item.id
        holder.binding.itemName.text = item.name + " : " + item.price.toString()
        if(item.cart){
            holder.binding.inCart.text = "in cart"
        }
        else{
            holder.binding.inCart.text = ""

        }
        holder.binding.itemid.setOnClickListener {
            itemClickListener?.onItemClick(item.id)
        }
        holder.binding.itemName.setOnClickListener {
            itemClickListener?.onItemClick(item.id)
        }
    }

    override fun getItemCount() = items.size
}