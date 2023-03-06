package fr.isen.mihalic.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import fr.isen.mihalic.androiderestaurant.databinding.ActivityHomeBinding

const val DEBUG_TAG = "RONRON"

const val EXTRA_STAGE = "fr.isen.mihalic.EXTRA_STAGE"
enum class Stage { ENTREE, MEAL, DESSERT }

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