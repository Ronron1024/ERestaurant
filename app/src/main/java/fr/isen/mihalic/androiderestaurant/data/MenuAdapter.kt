package fr.isen.mihalic.androiderestaurant.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.mihalic.androiderestaurant.R
import fr.isen.mihalic.androiderestaurant.databinding.MenuItemHolderBinding
import fr.isen.mihalic.androiderestaurant.utils.load

class MenuAdapter(private val menu: MutableList<MenuItem>, val onItemClicked: (String) -> Unit) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(binding: MenuItemHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageviewItemImage = binding.imageviewItemImage
        private val textviewItemTitle = binding.textviewItemTitle
        private val textviewItemPrice = binding.textviewItemPrice

        fun bind(data: MenuItem) {
            //TODO Picasso not loading image code HTTP 504
            //Picasso.get().isLoggingEnabled = true
            //TODO use fit
            Picasso.get().load(
                data.images[0].ifEmpty { null } ?: R.drawable.image_not_loaded
            ).error(R.drawable.image_not_loaded).into(imageviewItemImage)

            textviewItemTitle.text = data.title
            textviewItemPrice.text = data.price.toString()

            itemView.setOnClickListener {
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