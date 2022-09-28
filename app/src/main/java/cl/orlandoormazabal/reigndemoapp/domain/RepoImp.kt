package cl.orlandoormazabal.reigndemoapp.domain

import cl.orlandoormazabal.reigndemoapp.data.model.Hit

class RepoImp(private val remoteDataSource: RemoteDataSource): Repo {

    override suspend fun getRemoteHits(): List<Hit> {
        return remoteDataSource.getRemoteHits()
    }
}