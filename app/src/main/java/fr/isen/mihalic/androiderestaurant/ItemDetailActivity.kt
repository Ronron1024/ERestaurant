package fr.isen.mihalic.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ItemDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val item_id = intent.getIntExtra(EXTRA_ITEM_ID, -1)

        Log.d(DEBUG_TAG, MenuProvider[item_id].toString())
    }
}