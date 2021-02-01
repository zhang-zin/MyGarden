package com.zj.mygarden.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.zj.mygarden.R
import com.zj.mygarden.adapters.GardenPlantingAdapter
import com.zj.mygarden.adapters.PLANT_LIST_PAGE_INDEX
import com.zj.mygarden.adapters.PlantAdapter
import com.zj.mygarden.databinding.FragmentGardenBinding
import com.zj.mygarden.viewModels.GardenPlantingListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GardenFragment : Fragment() {

    val viewModel by viewModels<GardenPlantingListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGardenBinding.inflate(inflater, container, false)

        subscribeUi(binding)

        binding.addPlant.setOnClickListener {
            navigateToPlantListPage()
        }
        return binding.root
    }

    private fun subscribeUi(binding: FragmentGardenBinding) {
        val adapter = GardenPlantingAdapter()
        binding.gardenList.adapter = adapter
        viewModel.plantAndGardenPlantings.observe(viewLifecycleOwner) {
            binding.hasPlantings = !it.isNullOrEmpty()
            adapter.submitList(it)
        }
    }

    private fun navigateToPlantListPage() {
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem =
            PLANT_LIST_PAGE_INDEX
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            GardenFragment()
    }
}