package fr.isen.mihalic.androiderestaurant.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.mihalic.androiderestaurant.data.MenuAdapter
import fr.isen.mihalic.androiderestaurant.data.MenuProvider
import fr.isen.mihalic.androiderestaurant.databinding.ActivityMenuBinding

const val EXTRA_ITEM_ID = "fr.isen.mihalic.EXTRA_ITEM_ID"

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.menuRecyclerview
        recyclerView.adapter = MenuAdapter(mutableListOf(), ::getDetailOn)

        //TODO coroutines ?
        MenuProvider.fetchStage(getStage(), this) {
            for (item in it) {
                (recyclerView.adapter as MenuAdapter).addItem(item)
            }
        }
    }

    private fun getStage() : Stage {
        when (intent?.extras?.getInt(EXTRA_STAGE))
        {
            Stage.ENTREE.ordinal -> return Stage.ENTREE
            Stage.MEAL.ordinal -> return Stage.MEAL
            Stage.DESSERT.ordinal -> return Stage.DESSERT
        }
        return Stage.ENTREE
    }

    private fun getDetailOn(item_id: String)
    {
        val detailIntent = Intent(this, ItemDetailActivity::class.java)
        detailIntent.putExtra(EXTRA_ITEM_ID, item_id)
        startActivity(detailIntent)
    }
}