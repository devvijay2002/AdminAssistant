package com.example.modelresp


import com.google.gson.annotations.SerializedName

data class GrowthResp(
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("responseCode")
    val responseCode: Int,
    @SerializedName("result")
    val result: List<Result>
) {
    data class Result(
        @SerializedName("currentStatus")
        val currentStatus: String,
        @SerializedName("fromDate")
        val fromDate: String,
        @SerializedName("growthIndex")
        val growthIndex: String,
        @SerializedName("totalGrowth")
        val totalGrowth: String


    )


}