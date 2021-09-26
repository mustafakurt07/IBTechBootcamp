package com.example.ibtechbootcamphmfour.service

import com.example.ibtechbootcamphmfour.model.*
import com.example.ibtechbootcamphmfour.ui.HomeFragment
import retrofit2.Call
import retrofit2.http.*

interface TaskerAPI {
    @POST("user/login")
    fun login(@Body loginRequest: LoginRequest) : Call<LoginResponse>

    @GET("user/me")
    fun getMe() : Call<User>

    @POST("task")
    fun addTask(@Body todo: Todo): Call<Unit>

    @GET("task")
    fun getAllTasks(): Call<TodoResponse>

    @GET("task/{id}")
    fun getTaskById(@Path("id") id :Int): Call<Todo>

    @GET("task?completed=true")
    fun getTaskByCompleted(): Call<ArrayList<Todo>>

    @GET("task")
    fun getTaskByPagination(@Query("limit") limit: Int, @Query("skip") skip: Int): Call<TodoResponse>

   @PUT("task/{id}")
    fun updateTaskById(@Path("id") id: String, @Body completed:CompleteBody): Call<Unit>

    @DELETE("task/{id}")
    fun deleteTaskById(@Path("id") id: String): Call<Unit>
}