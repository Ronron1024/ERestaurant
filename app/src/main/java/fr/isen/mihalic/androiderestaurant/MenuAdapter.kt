package fr.isen.mihalic.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(private val menu: Array<String>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val menuTextView: Button = view.findViewById(R.id.menu_item);

        fun bind(data: String) {
            menuTextView.text = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.menu_item_holder, parent, false)
        return MenuViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return menu.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(menu[position])
    }

}