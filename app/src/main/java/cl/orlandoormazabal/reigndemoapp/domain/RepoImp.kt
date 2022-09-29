package cl.orlandoormazabal.reigndemoapp.domain

import cl.orlandoormazabal.reigndemoapp.connection.NetworkState
import cl.orlandoormazabal.reigndemoapp.data.model.Hit

class RepoImp(
    private val networkState: NetworkState,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
    ): Repo {

    override suspend fun getRemoteHits(): List<Hit> {
        return if(networkState.isInternetAvailable().isInternetAvailable) {
            remoteDataSource.getRemoteHits()
        } else {
            localDataSource.getLocalHits()
        }
    }
}