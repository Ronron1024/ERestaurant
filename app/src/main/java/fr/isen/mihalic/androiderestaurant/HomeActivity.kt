package fr.isen.mihalic.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun stageSelection(view: View)
    {
        when (view)
        {
            findViewById<Button>(R.id.button_entree) -> Toast.makeText(this, "Entree", Toast.LENGTH_SHORT).show()
            findViewById<Button>(R.id.button_meal) -> Toast.makeText(this, "Meal", Toast.LENGTH_SHORT).show()
            findViewById<Button>(R.id.button_dessert) -> Toast.makeText(this, "Dessert", Toast.LENGTH_SHORT).show()
        }
    }
}