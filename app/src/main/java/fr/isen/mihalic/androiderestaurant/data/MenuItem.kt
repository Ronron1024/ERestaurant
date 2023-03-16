package fr.isen.mihalic.androiderestaurant.data

data class MenuItem(
    private val _id: String,
    val title: String,
    val description: String,
    val price: Double,
    val category: String
) {
    val id: String get() = _id
}