package fr.isen.mihalic.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(private val menu: List<MenuItem>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val menuTextView: Button = view.findViewById(R.id.menu_item)

        fun bind(data: MenuItem) {
            menuTextView.text = data.title
            menuTextView.setOnClickListener {  }
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