package com.ubaya.advweek4160420147.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.advweek4160420147.R
import com.ubaya.advweek4160420147.databinding.CarsListItemsBinding
import com.ubaya.advweek4160420147.databinding.StudentListItemsBinding
import com.ubaya.advweek4160420147.model.Cars
import com.ubaya.advweek4160420147.util.loadImage

class CarsListAdapter(private var cars: List<Cars>) : RecyclerView.Adapter<CarsListAdapter.CarsViewHolder>() {

    class CarsViewHolder(val binding: CarsListItemsBinding) : RecyclerView.ViewHolder(binding.root)
    fun updateCarsList(newCars: List<Cars>) {
        cars = newCars
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val binding = CarsListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        val car = cars[position]
        holder.binding.txtID.text = car.model
        holder.binding.txtName.text = car.name
        holder.binding.txtColor.text = car.color
        holder.binding.txtYear.text = car.year
        holder.binding.txtPrice.text = car.price
        holder.binding.imgStory.loadImage(cars[position].imageUrl, holder.binding.progressBar)

        var imageView = holder.itemView.findViewById<ImageView>(R.id.imageView)
        var progressBar = holder.itemView.findViewById<ProgressBar>(R.id.progressBar)

//        holder.binding.btnDetail.setOnClickListener {
//            val action = StudentListFragmentDirections.actionStudentDetail()
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    override fun getItemCount(): Int = cars.size
}
