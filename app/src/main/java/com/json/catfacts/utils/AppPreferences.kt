package com.json.catfacts.utils

import android.content.Context
import androidx.core.content.edit
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.Calendar
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AppPreferences @Inject constructor(private val context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    var shouldFetchData: Boolean
        get() = sharedPreferences.getBoolean("shouldFetchData", true)
        set(value) = sharedPreferences.edit {
            putBoolean("shouldFetchData", value)
        }

    fun setVariableAtMidnight() {
        val currentDate = Calendar.getInstance()
        val midnight = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }
        val timeUntilMidnight = midnight.timeInMillis - currentDate.timeInMillis

        val workerRequest = OneTimeWorkRequestBuilder<GetCatFactWorker>()
            .setInitialDelay(timeUntilMidnight, TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(context).enqueue(workerRequest)
    }
}