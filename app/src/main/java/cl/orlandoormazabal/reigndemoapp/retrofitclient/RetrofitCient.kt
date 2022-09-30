package cl.orlandoormazabal.reigndemoapp.retrofitclient

import cl.orlandoormazabal.reigndemoapp.BuildConfig
import cl.orlandoormazabal.reigndemoapp.domain.WebService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val webService: WebService by lazy {
        Retrofit
            .Builder()
            .baseUrl(BuildConfig.SERVICE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(WebService::class.java)
    }
}