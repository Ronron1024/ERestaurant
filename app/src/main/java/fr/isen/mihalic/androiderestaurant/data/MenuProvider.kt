package fr.isen.mihalic.androiderestaurant.data

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.mihalic.androiderestaurant.activities.DEBUG_TAG
import fr.isen.mihalic.androiderestaurant.activities.Stage
import fr.isen.mihalic.androiderestaurant.data.jsonentities.CateringAPIResult
import org.json.JSONObject

object MenuProvider {

    //private const val API_URL = "http://192.168.14.1:3000"
    private const val API_URL = "http://test.api.catering.bluecodegames.com/menu"

    private val gson = Gson()

    private var menu: MutableMap<Stage, MutableList<MenuItem>> = mutableMapOf()
    private lateinit var queue: RequestQueue

    fun fetchStage(stage: Stage, context: Context, callback: ((List<MenuItem>) -> Unit)? = null) {
        if (!MenuProvider::queue.isInitialized)
            queue = Volley.newRequestQueue(context)

        if (menu[stage] == null)
            fetch(stage, callback)
        else
            callback?.invoke(menu[stage] ?: listOf())
    }

    private fun fetch(stage: Stage, callback: ((List<MenuItem>) -> Unit)? = null) {
        menu[stage] = mutableListOf()

        /*val request = JsonArrayRequest(
            "${API_URL}/${stage}",
            {
                for (i in 0 until it.length()) {
                    val item = it.getJSONObject(i)
                    menu[stage]?.add(gson.fromJson(item.toString(), MenuItem::class.java))
                }
                callback(menu[stage] ?: listOf())
            },
            {
                Log.e(DEBUG_TAG, it.toString())
            }
        )*/

        val request = JsonObjectRequest(
            Request.Method.POST,
            API_URL,
            JSONObject("{\"id_shop\":\"1\"}"),
            {
                val apiResult = gson.fromJson(it.toString(), CateringAPIResult::class.java)
                val category = when (stage) {
                    Stage.ENTREE -> apiResult.data[0]
                    Stage.MEAL -> apiResult.data[1]
                    Stage.DESSERT -> apiResult.data[2]
                }

                for (item in category.items) {
                    menu[stage]?.add(MenuItem(
                        item.id,
                        item.name_en,
                        item.name_en,
                        item.prices[0].price,
                        item.images,
                        category.name_fr
                    ))
                }

                callback?.invoke((menu[stage] ?: listOf()))
            },
            {
                Log.e(DEBUG_TAG, it.toString())
            }
        )

        queue.add(request)
    }

    operator fun get(index: String) : MenuItem? {
        for (list in menu.values) {
            val filtered = list.filter { it.id == index }
            if (filtered.isNotEmpty())
                return filtered[0] // assuming IDs are unique
        }
        return null
    }

}