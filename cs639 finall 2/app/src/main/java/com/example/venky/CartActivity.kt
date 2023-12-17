package com.example.venky

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.venky.R

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private val cartItems: MutableList<CartItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val receivedCartItems = intent.getSerializableExtra("cartItems") as ArrayList<CartItem>?
        if (receivedCartItems != null) {
            cartItems.addAll(receivedCartItems)
        }

        recyclerView = findViewById(R.id.cartRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        cartAdapter = CartAdapter(cartItems)
        recyclerView.adapter = cartAdapter

        val btnProceedToCheckout: Button = findViewById(R.id.btnProceedToCheckout)

        btnProceedToCheckout.setOnClickListener {

            onProceedToCheckoutButtonClick()
        }
    }

    private fun onProceedToCheckoutButtonClick() {
        Toast.makeText(this, "Order Purchased Successfully", Toast.LENGTH_SHORT).show()
        cartItems.clear()
        cartAdapter.notifyDataSetChanged()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
