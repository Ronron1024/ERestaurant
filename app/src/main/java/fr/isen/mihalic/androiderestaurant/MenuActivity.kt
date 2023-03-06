package fr.isen.mihalic.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import fr.isen.mihalic.androiderestaurant.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentStage = getStage()
        MenuProvider.fetchStage(currentStage)

        val recyclerView: RecyclerView = binding.menuRecyclerview
        recyclerView.adapter = MenuAdapter(MenuProvider.getMenuFor(currentStage))
    }

    private fun getStage() : Stage {
        when (intent?.extras?.getInt(EXTRA_STAGE))
        {
            Stage.ENTREE.ordinal -> return Stage.ENTREE
            Stage.MEAL.ordinal -> return Stage.MEAL
            Stage.DESSERT.ordinal -> return Stage.DESSERT
        }
        //TODO handle error when Stage not found (if necessary)
        return Stage.ENTREE
    }
}