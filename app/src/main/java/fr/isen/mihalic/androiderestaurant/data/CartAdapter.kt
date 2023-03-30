package fr.isen.mihalic.androiderestaurant.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.mihalic.androiderestaurant.R
import fr.isen.mihalic.androiderestaurant.databinding.CartItemBinding
import fr.isen.mihalic.androiderestaurant.utils.load

class CartAdapter(private val items: MutableList<Pair<MenuItem, Int>>, val onItemClicked: (Pair<MenuItem,Int>) -> Unit) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    inner class CartViewHolder(binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageviewItemImage = binding.imageviewItemImage
        private val textviewItemTitle = binding.textviewItemTitle
        private val textviewItemAmount = binding.textviewItemAmount
        private val buttonDelete = binding.buttonDelete

        fun bind(data: Pair<MenuItem, Int>) {

            val menuItem = MenuProvider[data.first.id]

            Picasso.get().load(
                menuItem?.images?.get(0)?.ifEmpty { null } ?: R.drawable.image_not_loaded
            ).error(R.drawable.image_not_loaded).into(imageviewItemImage)

            textviewItemTitle.text = "${data.second.toString()}x ${menuItem?.title}"
            textviewItemAmount.text = "\$ ${(menuItem?.price ?: 0.0) * data.second}"

            buttonDelete.setOnClickListener {
                onItemClicked(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val viewHolderBinding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(viewHolderBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun removeItem(item: Pair<MenuItem, Int>) {
        val index = items.indexOf(item)
        items.remove(item)
        notifyItemRemoved(index)
    }

    fun getItems(): List<Pair<MenuItem, Int>> {
        return items
    }
}