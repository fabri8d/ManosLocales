package com.undef.ManosLocales.data.remote

import com.undef.ManosLocales.data.remote.models.LoginResponse
import com.undef.ManosLocales.data.remote.models.SellerRegisterRequest
import com.undef.ManosLocales.data.remote.models.SellerDto
import com.undef.ManosLocales.data.remote.models.UserDto
import com.undef.ManosLocales.data.remote.models.UserLoginRequest
import com.undef.ManosLocales.data.remote.models.UserRegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("users/getUsers")
    suspend fun getAllUsers(): Response<List<UserDto>>

    @GET("users/getUserById/{id}")
    suspend fun getUserById(@Path("id") id: Int): Response<UserDto>

    @POST("/auth/login")
    suspend fun login(@Body request: UserLoginRequest): Response<LoginResponse>

    @POST("/auth/register")
    suspend fun registerUser(@Body user: UserRegisterRequest): Response<Void> // Cambiá `Void` si tu backend devuelve algo (como un token o el usuario)

    @GET("/sellers/getSellers")
    suspend fun getAllSellers(): Response<List<SellerDto>>

    @GET("sellers/getSellerByUserId/{userId}")
    suspend fun getSellerByUserId(@Path("userId") userId: Int): Response<SellerDto>

    @POST("sellers/createSeller")
    suspend fun createSeller(@Body request: SellerRegisterRequest): Response<SellerDto>

}
