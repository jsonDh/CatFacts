package com.json.catfacts.utils

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class GetCatFactWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val appPreferences: AppPreferences
) : Worker(context, workerParams){
    override fun doWork(): Result {
        appPreferences.shouldFetchData = true
        return Result.success()
    }
}