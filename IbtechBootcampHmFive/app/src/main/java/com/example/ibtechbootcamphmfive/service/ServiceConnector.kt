package com.example.ibtechbootcamphmfive.service

import com.example.ibtechbootcamphmfive.util.Constants
import com.example.ibtechbootcamphmfive.util.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.Response

class ServiceConnector {
    companion object {
        private var retrofit: Retrofit? = null
        lateinit var restInterface: MovieAPI

        fun init() {
           /* val logging = HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }*/
            //Instead of constantly adding keys, we added a request from one place.Api key added
            fun getOkHttpClient(): OkHttpClient {
                val client = OkHttpClient.Builder()
                client.addInterceptor(RequestInterceptor())
                return client.build()
            }

           /* val httpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()*/

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()

            restInterface = retrofit?.create(MovieAPI::class.java)!!
        }
    }



    class RequestInterceptor: Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", Constants.API_KEY)
                .build()

            val request = originalRequest.newBuilder().url(url).build()
            return chain.proceed(request)
        }

    }
}