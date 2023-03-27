package fr.isen.mihalic.androiderestaurant.utils

import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import fr.isen.mihalic.androiderestaurant.R
import fr.isen.mihalic.androiderestaurant.data.Cart
import fr.isen.mihalic.androiderestaurant.databinding.CartIconBinding

fun Picasso.load(resource: Any?): RequestCreator {
    return if (resource is String)
        this.load(resource)
    else if (resource is Int)
        this.load(resource)
    else
        this.load(resource as String?)
}

// https://stackoverflow.com/questions/64868186/increment-an-integer-map-value-in-kotlin
operator fun Int?.plus(other: Int) = this?.plus(other) ?: other

// https://stackoverflow.com/questions/33381384/how-to-use-typetoken-generics-with-gson-in-kotlin
inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object: TypeToken<T>() {}.type)