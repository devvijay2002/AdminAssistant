package com.example.admindata.util



import com.example.modelresp.GrowthResp
import com.example.modelresp.LoginResp
import com.example.modelresp.PostResp
import com.example.modelresp.ReportResp
import com.example.modelresp.SignUpResp
import com.example.modelresp.UpdatedUserResp
import com.example.modelresp.UserResp
import com.example.modelresp.UsersListResp
import io.reactivex.Observable
import retrofit2.http.Body


import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiInterface {

    @GET
    fun getUserData(
        @Url url: String,
        @Header("Referer") referer: String,
        @Header("Sec-Fetch-Site") secFetchSite: String
    ): Observable<UserResp>

    @GET
    fun getAllAssistantData(
        @Url url: String
    ) :Observable<UpdatedUserResp>

    @POST("/Assistant")
    fun PostUserData(
        @Query ("userId") userId:String,
        @Query ("followerCount") followerCount:Int,


    ) : Observable<PostResp>

    @POST("api/User")
    fun PostSignUPData(@Body requestBody: Map<String, String>): Observable<SignUpResp>

    @GET("/Login")
    fun getLoginData(
        @Query ("username") username:String,
        @Query ("password") password:String,
    ): Observable<LoginResp>

    @GET("Assistant/UserIds")
    fun getUsersList(
    ): Observable<UsersListResp>

    @GET
    fun getGrowth(
        @Url url: String,
    ): Observable<GrowthResp>

    @GET
    fun getReportList(
        @Url url: String,
        @Query("Month") Month:String,
        @Query("Year")  Year:String
    ): Observable<ReportResp>

}



