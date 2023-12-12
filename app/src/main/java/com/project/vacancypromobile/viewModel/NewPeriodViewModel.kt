package com.project.vacancypromobile.viewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.models.Place
import java.util.Date
import javax.inject.Inject


class NewPeriodViewModel @Inject constructor()   : ViewModel(){


    var periodName by mutableStateOf("")
    var periodDescription by mutableStateOf("")
     var periodStartDate by mutableStateOf<String>("")
     var periodEndDate by mutableStateOf<String>("")
    private var periodPlace by mutableStateOf("")

    fun updatePeriodName(periodName: String) {
        this.periodName = periodName
    }

    fun updatePeriodDescription(periodDescription: String) {
        this.periodDescription = periodDescription
    }

    fun updatePeriodStartDate(periodStartDate: String) {
        Log.d("Testing ", periodStartDate)
        this.periodStartDate = periodStartDate
    }

    fun updatePeriodEndDate(periodEndDate: String) {
        Log.d("Testing ", periodEndDate)
        this.periodEndDate = periodEndDate
    }

    fun updatePeriodPlace(periodPlace: String) {
        this.periodPlace = periodPlace
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun createPeriod() {
        Log.d("Testing ", "Wow ");


        val period = Period(
            name = periodName,
            description = periodDescription,
            beginDate = Date(),
            endDate = Date(),
            place = Place("null","null","null")

        );
        //periodRepository.createPeriod(period);


    }

}