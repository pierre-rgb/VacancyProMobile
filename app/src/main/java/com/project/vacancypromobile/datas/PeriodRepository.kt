package com.project.vacancypromobile.datas

import android.util.Log
import com.project.vacancypromobile.datas.interfaces.periods.CanAddUserToPeriod
import com.project.vacancypromobile.datas.interfaces.periods.CanDeletePeriods
import com.project.vacancypromobile.datas.interfaces.periods.CanGetAllPeriods
import com.project.vacancypromobile.datas.interfaces.periods.CanGetPeriodDetails
import com.project.vacancypromobile.models.Period
import com.project.vacancypromobile.services.ApiService
import com.project.vacancypromobile.services.requests.PeriodRequest
import java.io.Serializable
import java.text.SimpleDateFormat
import javax.inject.Inject

class PeriodRepository @Inject constructor(private  val apiService: ApiService)  : Serializable,
    CanGetAllPeriods, CanGetPeriodDetails  , CanDeletePeriods , CanAddUserToPeriod {


    private val _periods = mutableMapOf<Int, Period>()

    suspend fun createPeriod(request: PeriodRequest): Boolean {
        val response = apiService.createPeriod(request)
        if (response.isSuccessful && response.body() != null) {
            Log.d("Period", "Period created")
        } else {
            Log.d("Period", "Period not created")
        }
        return response.isSuccessful
    }

    override suspend fun getAllPeriods(): List<Period> {
        _periods.clear();
        val response = apiService.getAllPeriod()
        if (response.isSuccessful && response.body() != null) {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            response.body()?.forEach {
                _periods[it.id] = Period(it.id, it.name, it.description, format.parse(it.beginDate),  format.parse(it.endDate) , it.place ,emptyList())
            }
            Log.d("Period", "Periods found");
            return _periods.values.toList()
        } else {
            Log.d("Period", "Period not found")
            return emptyList()
        }
    }


     override fun getPeriodDetails(id : Int) : Period? {
        if(_periods.isEmpty() || !_periods.containsKey(id)) {
           return null
        }
        return _periods[id]
    }

    override suspend fun addUserToPeriod(userId: String, periodId: Int) : Boolean {
        val resp = apiService.addUserToPeriod(userId, periodId)
        return resp.isSuccessful
    }

    override suspend fun deletePeriod(id: Int) {
        apiService.deletePeriod(id)
    }


}



