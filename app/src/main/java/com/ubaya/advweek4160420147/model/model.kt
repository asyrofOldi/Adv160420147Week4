package com.ubaya.advweek4160420147.model

import com.google.gson.annotations.SerializedName
import java.time.Year

data class Student(
    var id:String?,
    @SerializedName("student_name")
    var name:String?,
    @SerializedName("birth_of_date")
    var dob:String?,
    var phone:String?,
    @SerializedName("photo_url")
    var photoUrl:String?
)
data class Cars(
    val id: String?,
    @SerializedName("make")
    val name: String?,
    val model:String?,
    val year:String?,
    val color: String?,
    val price: String?,
    val features:List<String>?,
    val imageUrl:String?,
    val specs:CarSpecifications?

)

data class CarSpecifications(
    val engine: String?,
    val transmission: String?,
    @SerializedName("fuel_type")
    val fuelType: String?,

)

