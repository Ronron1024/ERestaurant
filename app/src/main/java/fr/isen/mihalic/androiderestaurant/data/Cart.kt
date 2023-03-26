package fr.isen.mihalic.androiderestaurant.data

import android.content.Context
import com.google.gson.Gson
import fr.isen.mihalic.androiderestaurant.utils.plus
import java.io.File

object Cart {
    private const val CART_STORAGE_PATH = "cart.json"

    private val items = mutableMapOf<String, Int>()
    private val gson = Gson()

    fun addItem(context: Context, item: MenuItem, amount: Int) {
        items[item.id] += amount
        saveCart(context)
    }

    fun itemCount(): Int {
        return items.values.sum()
    }

    private fun saveCart(context: Context) {
        //TODO async writing ?
        val file = File(context.filesDir, CART_STORAGE_PATH)
        file.writeText(gson.toJson(items))
    }
}