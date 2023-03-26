package fr.isen.mihalic.androiderestaurant.utils

import com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback
import com.google.android.material.snackbar.Snackbar
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

class SnackbarCallback(
    private val dismissedAction: (Snackbar?, Int) -> Unit
) : BaseCallback<Snackbar>() {
    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
        super.onDismissed(transientBottomBar, event)
        dismissedAction(transientBottomBar, event)
    }

    override fun onShown(transientBottomBar: Snackbar?) {
        super.onShown(transientBottomBar)
    }
}