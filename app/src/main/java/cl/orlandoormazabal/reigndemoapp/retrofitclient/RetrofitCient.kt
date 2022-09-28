package cl.orlandoormazabal.reigndemoapp.retrofitclient

import cl.orlandoormazabal.reigndemoapp.domain.WebService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://hn.algolia.com/api/v1/"

object RetrofitClient {

    val webService: WebService by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(WebService::class.java)
    }
}