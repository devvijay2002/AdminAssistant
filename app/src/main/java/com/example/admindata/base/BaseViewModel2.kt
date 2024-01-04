package com.example.admindata.base


import androidx.lifecycle.ViewModel
import com.example.admindata.util.ApiInterface
import com.example.admindata.util.Retrofit2


abstract class BaseViewModel2 : ViewModel() {

    val api : ApiInterface by lazy {
        Retrofit2.createBaseApiService()

    }

}