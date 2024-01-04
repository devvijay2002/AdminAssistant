package com.example.admindata.base


import androidx.lifecycle.ViewModel
import com.example.admindata.util.ApiInterface
import com.example.admindata.util.Retrofit


abstract class BaseViewModel : ViewModel() {

    val api : ApiInterface by lazy {
        Retrofit.createBaseApiService()

    }

}