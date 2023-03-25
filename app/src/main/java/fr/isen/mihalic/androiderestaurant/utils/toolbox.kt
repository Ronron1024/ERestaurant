package fr.isen.mihalic.androiderestaurant.utils

import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

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