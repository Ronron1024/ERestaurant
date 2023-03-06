package fr.isen.mihalic.androiderestaurant

data class MenuItem(
    val id: Int,
    val title: String,
    val description: String = "Lorem ipsum dolor"
)