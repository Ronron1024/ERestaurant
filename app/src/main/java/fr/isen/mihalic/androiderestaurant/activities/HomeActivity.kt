package fr.isen.mihalic.androiderestaurant.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import fr.isen.mihalic.androiderestaurant.R
import fr.isen.mihalic.androiderestaurant.databinding.ActivityHomeBinding

const val DEBUG_TAG = "RONRON"

const val EXTRA_STAGE = "fr.isen.mihalic.EXTRA_STAGE"
enum class Stage(private val str: String) {
    ENTREE("entree"),
    MEAL("meal"),
    DESSERT("dessert");

    override fun toString(): String {
        return str
    }
}

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)
        binding.materialToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_cart -> {
                    Log.d(DEBUG_TAG, "cart clicked !")
                    true
                }
                else -> false
            }
        }

        binding.homeButtonEntree.setOnClickListener {
            openMenu(Stage.ENTREE)
        }

        binding.homeButtonMeal.setOnClickListener {
            openMenu(Stage.MEAL)
        }

        binding.homeButtonDessert.setOnClickListener {
            openMenu(Stage.DESSERT)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_bar, menu)
        return true
    }

    private fun openMenu(stage: Stage)
    {
        val menuIntent = Intent(this, MenuActivity::class.java)
        menuIntent.putExtra(EXTRA_STAGE, stage.ordinal)
        startActivity(menuIntent)
    }


}