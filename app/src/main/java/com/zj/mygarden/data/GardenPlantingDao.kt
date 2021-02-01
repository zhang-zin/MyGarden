package com.zj.mygarden.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GardenPlantingDao {

    @Query("select * from garden_plantings")
    fun getGardenPlantings(): Flow<List<GardenPlanting>>

    @Query("select exists(select 1 from garden_plantings where plant_id = :plantId limit 1)")
    fun isPlanted(plantId: String): Flow<Boolean>

    @Transaction
    @Query("SELECT * FROM plants WHERE id IN (SELECT DISTINCT(plant_id) FROM garden_plantings)")
    fun getPlantedGardens(): Flow<List<PlantAndGardenPlantings>>

    @Insert
    suspend fun insertGardenPlanting(gardenPlanting: GardenPlanting): Long

    @Delete
    suspend fun deleteGardenPlanting(gardenPlanting: GardenPlanting)
}