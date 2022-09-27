package com.dapoi.memeapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dapoi.memeapp.data.ApiClient
import com.dapoi.memeapp.data.DataItem
import com.dapoi.memeapp.data.MemeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MemeViewModel : ViewModel() {

    /**
     * private buat nerioma data dari end point
     *
     * non private buat dipanggil di tampilan
     */
    private val _dataMeme = MutableLiveData<List<DataItem>>()
    val dataMeme: LiveData<List<DataItem>> = _dataMeme


    /**
     *
     * fungsi ini untuk mengambil data dari end point
     *
     */
    fun getMeme() {
        val baseURL = ApiClient.getClient()
        val endPoint = baseURL.getMemes()
        endPoint.enqueue(object : Callback<MemeResponse> {
            override fun onResponse(call: Call<MemeResponse>, response: Response<MemeResponse>) {
                if (response.isSuccessful) {
                    _dataMeme.value = response.body()?.data
                }
            }

            override fun onFailure(call: Call<MemeResponse>, t: Throwable) {
                Log.d("Error nih ngab", t.message.toString())
            }

        })
    }

}