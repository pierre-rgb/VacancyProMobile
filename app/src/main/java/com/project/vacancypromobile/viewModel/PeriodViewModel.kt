package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.models.Period

class PeriodViewModel constructor(period: Period) : ViewModel() {
    val nom by mutableStateOf(period.name)
    val description by mutableStateOf(period.description)
    val urlPhoto by mutableStateOf(period.place.urlPhoto)
    val date by mutableStateOf(period.afficherDate())

}
