package com.example.venky
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private var cartItems: List<CartItem>) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.textCartItemName)
        val itemPrice: TextView = itemView.findViewById(R.id.textCartItemPrice)
        val deleteButton: Button = itemView.findViewById(R.id.btnDelete)
        val itemImage: ImageView = itemView.findViewById(R.id.imageCartItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartItem = cartItems[position]

        holder.itemName.text = cartItem.itemName
        holder.itemPrice.text = "$${cartItem.itemPrice}"
        holder.itemImage.setImageResource(cartItem.imageResourceId)

        holder.deleteButton.setOnClickListener {
            onDeleteButtonClick(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    private fun onDeleteButtonClick(position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val updatedCartItems = cartItems.toMutableList()
            updatedCartItems.removeAt(position)
            cartItems = updatedCartItems
            notifyItemRemoved(position)
        }
    }
}
