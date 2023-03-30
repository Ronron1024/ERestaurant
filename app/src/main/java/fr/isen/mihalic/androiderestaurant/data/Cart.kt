package fr.isen.mihalic.androiderestaurant.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import fr.isen.mihalic.androiderestaurant.activities.DEBUG_TAG
import fr.isen.mihalic.androiderestaurant.utils.fromJson
import fr.isen.mihalic.androiderestaurant.utils.plus
import java.io.File
import java.io.IOException

object Cart {
    private const val CART_STORAGE_PATH = "cart.json"

    private var items = mutableMapOf<String, Int>()
    private val gson = Gson()

    fun addItem(context: Context, item: MenuItem, amount: Int) {
        items[item.id] += amount
        saveCart(context)
    }

    fun removeItem(context: Context, item: MenuItem)
    {
        items.remove(item.id)
        saveCart(context)
    }

    fun clear(context: Context) {
        items.clear()
        saveCart(context)
    }

    fun itemCount(context: Context): Int {
        return getCart(context).values.sum()
    }

    private fun saveCart(context: Context) {
        //TODO async writing ?
        val file = File(context.filesDir, CART_STORAGE_PATH)
        file.writeText(gson.toJson(items))
    }

    fun getCart(context: Context): Map<String, Int> {
        items.clear()

        try {
            val file = File(context.filesDir, CART_STORAGE_PATH)
            items = gson.fromJson<MutableMap<String, Int>>(file.readText())
        } catch (e: IOException) {
            Log.e(DEBUG_TAG, e.toString())
        }

        return items
    }
}