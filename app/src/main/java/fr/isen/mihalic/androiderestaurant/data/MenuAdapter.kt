package fr.isen.mihalic.androiderestaurant.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.mihalic.androiderestaurant.R
import fr.isen.mihalic.androiderestaurant.databinding.MenuItemHolderBinding

fun String.nullIfEmpty(): String? {
    return this.ifEmpty { null }
}

class MenuAdapter(private val menu: MutableList<MenuItem>, val onItemClicked: (String) -> Unit) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(binding: MenuItemHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageviewItemImage = binding.imageviewItemImage
        private val textviewItemTitle = binding.textviewItemTitle
        private val textviewItemPrice = binding.textviewItemPrice

        fun bind(data: MenuItem) {
            if (data.image.isEmpty())
                Picasso.get().load(R.drawable.image_not_loaded_24).into(imageviewItemImage)
            else
                Picasso.get().load(data.image).into(imageviewItemImage)
            textviewItemTitle.text = data.title
            textviewItemPrice.text = data.price.toString()

            textviewItemTitle.setOnClickListener {
                onItemClicked(data.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val viewHolderBinding = MenuItemHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(viewHolderBinding)
    }

    override fun getItemCount(): Int = menu.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(menu[position])
    }

    fun addItem(item: MenuItem) {
        menu.add(item)
        notifyItemInserted(menu.indexOf(item))
    }

}