package com.example.venky
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import android.widget.Toast
import ProductAdapter
class MainActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var productList: List<Product>
    private lateinit var productAdapter: ProductAdapter
    private lateinit var cartIcon: ImageView
    private val cartItems: MutableList<CartItem> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productList = getSampleProducts()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(productList) { product ->
            addToCart(product)
        }
        recyclerView.adapter = productAdapter

        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                productAdapter.filter(newText)
                return true
            }
        })

        cartIcon = findViewById(R.id.cartIcon)

        cartIcon.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("cartItems", ArrayList(cartItems))
            startActivity(intent)
        }
    }

    private fun getSampleProducts(): List<Product> {
        return listOf(
            Product(2, "nikeair", 15.99, R.drawable.nikeair),
            Product(3, "choclates", 59.99, R.drawable.chocolates),
            Product(4, "boots", 49.99, R.drawable.boots),
            Product(5, "shirt", 12.99, R.drawable.shirt),
            Product(6, "orangesweater", 29.99, R.drawable.orangesweater),
            Product(7, "socks", 19.99, R.drawable.socks)
        )
    }
    fun addToCart(product: Product) {
        cartItems.add(CartItem(product.name, product.price, product.imageResourceId))

        val message = "${product.name} added to cart"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}
