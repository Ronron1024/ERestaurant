package fr.isen.mihalic.androiderestaurant.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import fr.isen.mihalic.androiderestaurant.R
import fr.isen.mihalic.androiderestaurant.data.Cart
import fr.isen.mihalic.androiderestaurant.data.MenuAdapter
import fr.isen.mihalic.androiderestaurant.data.MenuItem
import fr.isen.mihalic.androiderestaurant.data.MenuProvider
import fr.isen.mihalic.androiderestaurant.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTopBar(binding.cartTopBar.materialToolbar)

        val items = mutableListOf<MenuItem>()
        for (item in Cart.getCart(this)) {
            MenuProvider[item.key]?.let { items.add(it) }
        }

        val recyclerView = binding.cartRecyclerview
        recyclerView.adapter = MenuAdapter(items) {
            Log.d(DEBUG_TAG, it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_bar, menu)
        return true
    }
}