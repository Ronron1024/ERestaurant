package fr.isen.mihalic.androiderestaurant.data.jsonentities

data class CateringAPIResult(
    val data: List<Category>
) {
    data class Category(
        val name_fr: String,
        val name_en: String,
        val items: List<MenuItem>
    )

    data class MenuItem(
        val id: String,
        val name_fr: String,
        val name_en: String,
        val id_category: Int,
        val categ_name_fr: String,
        val categ_name_en: String,
        val images: List<String>,
        val ingredients: List<Ingredient>,
        val prices: List<Price>
    )

    data class Ingredient(
        val id: Int,
        val id_shop: Int,
        val name_fr: String,
        val name_en: String,
        val create_date: String,
        val update_date: String,
        val id_pizza: Int
    )

    data class Price(
        val id: Int,
        val id_pizza: Int,
        val id_size: Int,
        val price: Double,
        val create_date: String,
        val update_date: String,
        val size: String
    )
}