package com.project.vacancypromobile.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.models.Period
import javax.inject.Inject


class NewPeriodViewModel @Inject constructor()   : ViewModel(){


    var periodName by mutableStateOf("")
    var periodDescription by mutableStateOf("")
    private var periodStartDate by mutableStateOf<Any>("")
    private var periodEndDate by mutableStateOf<Any>("")
    private var periodPlace by mutableStateOf("")

    fun updatePeriodName(periodName: String) {
        this.periodName = periodName
    }

    fun updatePeriodDescription(periodDescription: String) {
        this.periodDescription = periodDescription
    }

    fun updatePeriodStartDate(periodStartDate: Any) {
        this.periodStartDate = periodStartDate
    }

    fun updatePeriodEndDate(periodEndDate: Any) {
        this.periodEndDate = periodEndDate
    }

    fun updatePeriodPlace(periodPlace: String) {
        this.periodPlace = periodPlace
    }

    suspend fun createPeriod() {
        Log.d("Testing ", "Wow ");


        val period = Period(
            name = periodName,
            description = periodDescription);
        //periodRepository.createPeriod(period);


    }

}