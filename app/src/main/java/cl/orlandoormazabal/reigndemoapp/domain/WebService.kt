package cl.orlandoormazabal.reigndemoapp.domain

import cl.orlandoormazabal.reigndemoapp.data.model.HitList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("search_by_date")
    suspend fun getHits(
        @Query(value = "query")
        deviceType: String = "mobile"): HitList
}