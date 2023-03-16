package fr.isen.mihalic.androiderestaurant.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.isen.mihalic.androiderestaurant.data.MenuProvider
import fr.isen.mihalic.androiderestaurant.R

class ItemDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val itemID = intent.getStringExtra(EXTRA_ITEM_ID).toString()

        Log.d(DEBUG_TAG, MenuProvider[itemID].toString())
    }
}