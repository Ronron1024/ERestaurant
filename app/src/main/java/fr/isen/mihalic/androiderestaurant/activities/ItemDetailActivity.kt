package fr.isen.mihalic.androiderestaurant.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.behavior.SwipeDismissBehavior
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_SLIDE
import com.google.android.material.snackbar.Snackbar
import fr.isen.mihalic.androiderestaurant.R
import fr.isen.mihalic.androiderestaurant.data.MenuProvider
import fr.isen.mihalic.androiderestaurant.data.CarouselAdapter
import fr.isen.mihalic.androiderestaurant.data.Cart
import fr.isen.mihalic.androiderestaurant.data.MenuItem
import fr.isen.mihalic.androiderestaurant.databinding.ActivityItemDetailBinding

class ItemDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemDetailBinding

    private var item: MenuItem? = null

    private var quantity = 0
    private var totalPrice = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemID = intent.getStringExtra(EXTRA_ITEM_ID).toString()
        item = MenuProvider[itemID]

        // Initial item
        addItem(1)

        binding.pager.adapter = CarouselAdapter(item?.images ?: listOf())

        binding.detailItemTitle.text = item?.title

        binding.detailButtonMinus.setOnClickListener {
            addItem(-1)
        }
        binding.detailButtonPlus.setOnClickListener {
            addItem(1)
        }

        binding.detailButtonAdd.setOnClickListener {
            Snackbar.make(it, R.string.added_to_cart_info, Snackbar.LENGTH_SHORT).show()
        }
    }

    //TODO Separate data from view
    @Suppress("MemberVisibilityCanBePrivate")
    fun addItem(amount: Int) {
        if (quantity+amount < 1)
            return

        quantity += amount
        totalPrice = quantity * (item?.price ?: 0.0)
        item?.let { Cart.addItem(this, it, amount) }
        updateValues()
    }

    private fun updateValues() {
        binding.detailQuantity.text = quantity.toString()
        // Cast totalPrice to string in order to get adapted precision
        binding.detailButtonAdd.text = getString(R.string.detail_button_add_to_cart_default_value, totalPrice.toString())
    }
}