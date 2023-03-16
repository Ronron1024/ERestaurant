package fr.isen.mihalic.androiderestaurant.data

data class MenuItem(
    val id: String,
    val title: String,
    val description: String,
    val price: Double,
    val category: String
)