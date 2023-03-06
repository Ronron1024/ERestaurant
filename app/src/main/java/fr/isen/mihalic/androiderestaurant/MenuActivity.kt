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

        val recyclerView: RecyclerView = binding.menuRecyclerview
        val prefix = when (currentStage) {
            Stage.ENTREE -> "ENTREE"
            Stage.MEAL -> "MEAL"
            Stage.DESSERT -> "DESSERT"
        }
        val data = Array(10) {i -> "$prefix$i" }
        recyclerView.adapter = MenuAdapter(data)
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