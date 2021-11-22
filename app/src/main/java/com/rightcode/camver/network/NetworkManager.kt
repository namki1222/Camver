package com.rightcode.camver.network


import android.content.Context
import com.rightcode.camver.Features
import com.rightcode.camver.Util.PreferenceUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManager {
    private val domain: String = "http://52.79.181.134:9300";
    private var retrofit: Retrofit? = null
    var api: NetworkApi? = null

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(domain)
            .addConverterFactory(GsonConverterFactory.create())
            .client(buildClient())
            .build()
        api = retrofit?.create(NetworkApi::class.java)
    }

    companion object {
        lateinit var context: Context
        var sIntance: NetworkManager? = null

        fun getInstance(_context: Context): NetworkManager? {
            return sIntance ?: synchronized(this) {
                sIntance ?: NetworkManager().also {
                    context = _context
                    sIntance = it
                }
            }
        }
    }

    private fun buildClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(Interceptor { chain: Interceptor.Chain ->
            val token: String? = PreferenceUtil.getInstance(context)
                .get(PreferenceUtil.PreferenceKey.Token, "") //TODO: 토큰 PreferenceUtil에서 받아오기

            val newRequest: Request = if (token != null && token != "") {
                chain.request().newBuilder().addHeader("Authorization", "bearer $token").build()
            } else chain.request()
            chain.proceed(newRequest)
        })
        if (Features.TEST_ONLY && Features.SHOW_NETWORK_LOG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(logging)
        }
        builder.retryOnConnectionFailure(true)
        return builder.build()
    }

    fun getApiService(): NetworkApi? {
        return api
    }
}