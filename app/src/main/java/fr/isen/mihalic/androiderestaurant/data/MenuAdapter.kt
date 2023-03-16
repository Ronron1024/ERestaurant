package fr.isen.mihalic.androiderestaurant.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.isen.mihalic.androiderestaurant.databinding.MenuItemHolderBinding

class MenuAdapter(val menu: MutableList<MenuItem>, val onItemClicked: (String) -> Unit) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(binding: MenuItemHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        private val elementView = binding.menuItem

        fun bind(data: MenuItem) {
            elementView.text = data.title

            elementView.setOnClickListener {
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