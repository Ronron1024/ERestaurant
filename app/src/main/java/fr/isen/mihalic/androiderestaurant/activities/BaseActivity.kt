package fr.isen.mihalic.androiderestaurant.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import fr.isen.mihalic.androiderestaurant.R
import fr.isen.mihalic.androiderestaurant.data.Cart
import fr.isen.mihalic.androiderestaurant.databinding.CartIconBinding

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var cartIconBinding: CartIconBinding

    fun setTopBar(topBar: MaterialToolbar) {
        setSupportActionBar(topBar)
        topBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_cart -> {
                    Log.d(DEBUG_TAG, "cart clicked !")
                    true
                }
                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        invalidateOptionsMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_bar, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val cartItem = menu.findItem(R.id.action_cart).actionView

        cartItem?.let { cartIconBinding = CartIconBinding.bind(it) }

        val articleCount = Cart.itemCount(this)
        if (articleCount > 0) {
            cartIconBinding.badgeArticleCount.text = articleCount.toString()
            cartIconBinding.badgeArticleCount.visibility = View.VISIBLE
        }

        return super.onPrepareOptionsMenu(menu)
    }

}