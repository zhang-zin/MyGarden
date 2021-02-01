package com.zj.mygarden.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zj.mygarden.R
import com.zj.mygarden.data.PlantAndGardenPlantings
import com.zj.mygarden.databinding.ListItemGardenPlantingBinding
import com.zj.mygarden.fragment.HomeFragmentDirections
import com.zj.mygarden.viewModels.PlantAndGardenPlantingsViewModel

class GardenPlantingAdapter :
    ListAdapter<PlantAndGardenPlantings, GardenPlantingAdapter.GardenPlantingViewMode>(
        GardenPlantingDiffCallback()
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardenPlantingViewMode {
        val inflate = DataBindingUtil.inflate<ListItemGardenPlantingBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_garden_planting,
            parent,
            false
        )
        return GardenPlantingViewMode(inflate)
    }

    override fun onBindViewHolder(holder: GardenPlantingViewMode, position: Int) {
        holder.bind(getItem(position))
    }

    class GardenPlantingViewMode(private val binding: ListItemGardenPlantingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener { view ->
                binding.viewModel?.plantId?.let { plantId ->
                    navigateToPlant(plantId, view)
                }
            }
        }

        private fun navigateToPlant(plantId: String, view: View) {
            val direction = HomeFragmentDirections
                .actionHomeFragmentToPlantDetailFragment(plantId)
            view.findNavController().navigate(direction)
        }


        fun bind(item: PlantAndGardenPlantings) {
            with(binding) {
                binding.viewModel = PlantAndGardenPlantingsViewModel(item)
                executePendingBindings()
            }
        }

    }

}

private class GardenPlantingDiffCallback : DiffUtil.ItemCallback<PlantAndGardenPlantings>() {

    override fun areItemsTheSame(
        oldItem: PlantAndGardenPlantings,
        newItem: PlantAndGardenPlantings
    ): Boolean {
        return oldItem.plant.plantId == newItem.plant.plantId
    }

    override fun areContentsTheSame(
        oldItem: PlantAndGardenPlantings,
        newItem: PlantAndGardenPlantings
    ): Boolean {
        return oldItem.plant == newItem.plant
    }
}