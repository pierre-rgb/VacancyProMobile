package com.project.vacancypromobile.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Period (
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("beginDate")
    var beginDate : Date,
    @SerializedName("endDate")
    var endDate : Date,
    @SerializedName("place")
    var place : Place
)