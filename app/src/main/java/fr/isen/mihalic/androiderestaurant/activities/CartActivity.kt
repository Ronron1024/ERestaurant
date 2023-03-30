package fr.isen.mihalic.androiderestaurant.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import fr.isen.mihalic.androiderestaurant.R
import fr.isen.mihalic.androiderestaurant.data.*
import fr.isen.mihalic.androiderestaurant.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTopBar(binding.cartTopBar.materialToolbar)

        val items = mutableListOf<Pair<MenuItem, Int>>()
        for (item in Cart.getCart(this)) {
            MenuProvider[item.key]?.let { items.add(it to item.value) }
        }

        val recyclerView = binding.cartRecyclerview
        recyclerView.adapter = CartAdapter(items) {
            Cart.removeItem(this, it.first)
            (recyclerView.adapter as CartAdapter).removeItem(it)
            invalidateOptionsMenu()

            if (Cart.itemCount(this) <= 0)
                finish()
        }

        binding.buttonOrder.setOnClickListener {
            Cart.clear(this)
            Toast.makeText(this, "Ordered !", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    // Prevent loop on CartActivity
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_bar, menu)
        return true
    }
}