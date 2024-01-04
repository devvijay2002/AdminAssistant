package com.example.modelresp


import com.google.gson.annotations.SerializedName

data class ReportResp(
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
        @SerializedName("date")
        val date: String,
        @SerializedName("status")
        val status: String
    )
}