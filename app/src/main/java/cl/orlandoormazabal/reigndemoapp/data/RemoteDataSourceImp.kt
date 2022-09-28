package cl.orlandoormazabal.reigndemoapp.data

import cl.orlandoormazabal.reigndemoapp.data.model.Hit
import cl.orlandoormazabal.reigndemoapp.domain.RemoteDataSource
import cl.orlandoormazabal.reigndemoapp.retrofitclient.RetrofitClient

class RemoteDataSourceImp : RemoteDataSource {

    override suspend fun getRemoteHits(): List<Hit> {
        return RetrofitClient.webService.getHits().hitList
    }
}