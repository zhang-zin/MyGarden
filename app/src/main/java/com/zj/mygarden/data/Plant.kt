package com.zj.mygarden.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import java.util.Calendar.DAY_OF_YEAR

@Entity(tableName = "plants")
data class Plant(
    @PrimaryKey @ColumnInfo(name = "id")
    val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int = 7, // how often the plant should be watered, in days
    val imageUrl: String = ""
) {

    /**
     * 确定是否应给植物浇水。
     * [since]
     * [lastWateringData]
     */
    fun shouldBeWatered(since: Calendar, lastWateringData: Calendar) =
        since > lastWateringData.apply {
            add(DAY_OF_YEAR, wateringInterval)
        }

    override fun toString() = name
}