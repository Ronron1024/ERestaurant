package fr.isen.mihalic.androiderestaurant.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.mihalic.androiderestaurant.R
import fr.isen.mihalic.androiderestaurant.databinding.CarouselItemBinding
import fr.isen.mihalic.androiderestaurant.utils.load

class CarouselAdapter(private val data: List<String>) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    inner class CarouselViewHolder(binding: CarouselItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val image = binding.carouselImage

        fun bind(data: String) {
            Picasso.get().isLoggingEnabled = true
            Picasso.get().load(data.ifEmpty { null } ?: R.drawable.image_not_loaded).error(R.drawable.image_not_loaded).into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding = CarouselItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(data[position])
    }

}