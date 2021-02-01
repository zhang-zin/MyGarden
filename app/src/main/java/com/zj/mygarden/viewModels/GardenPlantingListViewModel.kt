package com.zj.mygarden.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.zj.mygarden.data.GardenPlantingRepository
import com.zj.mygarden.data.PlantAndGardenPlantings

class GardenPlantingListViewModel @ViewModelInject internal constructor(gardenPlantingRepository: GardenPlantingRepository) :
    ViewModel() {

    val plantAndGardenPlantings: LiveData<List<PlantAndGardenPlantings>> =
        gardenPlantingRepository.getPlantedGardens().asLiveData()
}