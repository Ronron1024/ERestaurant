package fr.isen.mihalic.androiderestaurant.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    private fun openMenu(stage: Stage)
    {
        val menuIntent = Intent(this, MenuActivity::class.java)
        menuIntent.putExtra(EXTRA_STAGE, stage.ordinal)
        startActivity(menuIntent)
    }
}