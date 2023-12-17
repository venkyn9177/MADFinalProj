
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.venky.Product
import com.example.venky.R
import android.widget.Toast
import com.example.venky.CartItem
import com.example.venky.MainActivity

class ProductAdapter(
    private var productList: List<Product>,
    private val onAddToCartClickListener: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>(), Filterable {

    private var filteredList: List<Product> = productList
    private val cartItems: MutableList<CartItem> = mutableListOf()
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.textProductName)
        val productPrice: TextView = itemView.findViewById(R.id.textProductPrice)
        val productImage: ImageView = itemView.findViewById(R.id.imageProduct)
        val btnBuyNow: Button = itemView.findViewById(R.id.btnBuyNow)
        val btnAddToCart: Button = itemView.findViewById(R.id.btnAddToCart)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = filteredList[position]

        holder.productName.text = product.name
        holder.productPrice.text = "$${product.price}"
        holder.productImage.setImageResource(product.imageResourceId)

        holder.btnBuyNow.setOnClickListener {

            purchaseItem(holder, product.name)
        }

        holder.btnAddToCart.setOnClickListener {
                onAddToCartClickListener.invoke(product)
        }
    }

    private fun purchaseItem(holder: ViewHolder, itemName: String) {
        val message = "$itemName purchased successfully"
        Toast.makeText(holder.itemView.context, message, Toast.LENGTH_SHORT).show()
    }
    private fun addToCart(holder: ViewHolder, product: Product) {

        (holder.itemView.context as? MainActivity)?.addToCart(product)

        val message = "${product.name} added to cart"
        Toast.makeText(holder.itemView.context, message, Toast.LENGTH_SHORT).show()
    }


    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint.toString()
                val filteredProducts = if (query.isEmpty()) {
                    productList
                } else {
                    productList.filter {
                        it.name.contains(query, ignoreCase = true) ||
                                it.price.toString().contains(query, ignoreCase = true)
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredProducts
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as List<Product>
                notifyDataSetChanged()
            }
        }
    }

    fun filter(newText: String?) {

        filter.filter(newText)
    }
}
