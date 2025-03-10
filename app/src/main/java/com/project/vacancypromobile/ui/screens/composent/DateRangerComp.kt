package com.project.vacancypromobile.ui.screens.composent

import android.icu.util.Calendar
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangeComp(
    modifier: Modifier = Modifier,
    selectStartDate: (String) -> Unit = {},
    selectEndDate: (String) -> Unit = {},
    beginDateStr: String = "",
    endDateStr:String= ""
) {


    var startDate by remember {
        mutableLongStateOf(0) // or use mutableStateOf(calendar.timeInMillis)
    }
    var endDate by remember {
        mutableLongStateOf(0) // or use mutableStateOf(calendar.timeInMillis)
    }
    val calendar = Calendar.getInstance()
    if(beginDateStr != "" && endDateStr != "") {

        calendar.set(beginDateStr.substring(0,4).toInt(), beginDateStr.substring(5,7).toInt(), beginDateStr.substring(8,10).toInt())
        startDate = calendar.timeInMillis;
        calendar.set(endDateStr.substring(0,4).toInt(), endDateStr.substring(5,7).toInt(), endDateStr.substring(8,10).toInt())
        endDate = calendar.timeInMillis;

    }else {
        calendar.set(2023, 11, 6)
        startDate = calendar.timeInMillis;// year, month, date
        calendar.set(2023, 11, 6)
        endDate = calendar.timeInMillis;// year, month, date
    }

    // set the initial dates
    val dateRangePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = startDate,
        initialSelectedEndDateMillis = endDate
    )

    var showDateRangePicker by remember {
        mutableStateOf(false)
    }

    if (showDateRangePicker) {
        DatePickerDialog(
            onDismissRequest = {
                showDateRangePicker = false
            },
            confirmButton = {
                TextButton(onClick = {
                    if (dateRangePickerState.selectedStartDateMillis != null && dateRangePickerState.selectedEndDateMillis != null) {
                        showDateRangePicker = false
                        val startDateTemp = dateRangePickerState.selectedStartDateMillis!!
                        val endDateTemp = dateRangePickerState.selectedEndDateMillis!!

                        val dateDebut = convertDate(startDateTemp)
                        val dateFin = convertDate(endDateTemp)

                        selectStartDate(dateDebut)
                        selectEndDate(dateFin)
                    }

                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDateRangePicker = false
                }) {
                    Text(text = "Cancel")
                }
            }
        ) {
            DateRangePicker(
                state = dateRangePickerState,
                modifier = Modifier.height(height = 500.dp),
            )
        }
    }

    Button(
        onClick = {
            showDateRangePicker = true
        },
        modifier = modifier,
    ) {
        Text(text = "Ajouter les dates")
    }
}


fun convertDate(dateInMillis: Long): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
    val calendar = Calendar.getInstance();
    calendar.timeInMillis = dateInMillis;
    return (formatter.format(Date(calendar.timeInMillis)));
}





