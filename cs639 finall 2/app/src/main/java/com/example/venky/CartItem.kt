package com.example.venky

import java.io.Serializable

data class CartItem(val itemName: String, val itemPrice: Double, val imageResourceId: Int) : Serializable
