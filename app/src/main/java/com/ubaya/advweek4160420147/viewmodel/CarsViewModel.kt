package com.ubaya.advweek4160420147.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.advweek4160420147.model.Cars

class CarsViewModel(application: Application) : AndroidViewModel(application) {
    val carsLD = MutableLiveData<List<Cars>>()
    val carsLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        carsLoadErrorLD.value = false
        loadingLD.value = true
        Log.d("CekMasuk", "masukvolley")
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://172.20.10.7/cars/cars.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                try {
                    val gson = Gson()
                    val cars = gson.fromJson(response, Array<Cars>::class.java).toList()
                    carsLD.value = cars
                    carsLoadErrorLD.value = false
                    loadingLD.value = false
                    Log.d("showVolley", cars.toString())
                } catch (e: Exception) {
                    Log.e(TAG, "Error parsing JSON: ", e)
                    carsLoadErrorLD.value = true
                    loadingLD.value = false
                }
            },
            { error ->
                Log.e(TAG, "Volley error: $error")
                carsLoadErrorLD.value = true
                loadingLD.value = false
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }


    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}
