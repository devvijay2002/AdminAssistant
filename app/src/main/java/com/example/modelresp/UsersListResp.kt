package com.example.modelresp


import com.google.gson.annotations.SerializedName

data class UsersListResp(
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
        @SerializedName("username")
        val username: String
    )
}