package com.zj.mygarden.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.zj.mygarden.data.AppDatabase
import com.zj.mygarden.data.Plant
import com.zj.mygarden.utilities.PLANT_DATA_FILENAME
import java.lang.Exception

class SeedDatabaseWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(
    context,
    workerParams
) {
    override suspend fun doWork(): Result {
        try {
            applicationContext.assets.open(PLANT_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val plantType = object : TypeToken<List<Plant>>() {}.type
                    val plants: List<Plant> = Gson().fromJson(jsonReader, plantType)
                    val plantDao = AppDatabase.getInstance(applicationContext).plantDao()
                    plantDao.insertAll(plants)
                    return Result.success()
                }
            }
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}