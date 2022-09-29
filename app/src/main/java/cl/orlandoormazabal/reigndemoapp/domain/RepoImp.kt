package cl.orlandoormazabal.reigndemoapp.domain

import cl.orlandoormazabal.reigndemoapp.base.connection.NetworkState
import cl.orlandoormazabal.reigndemoapp.data.model.Hit
import cl.orlandoormazabal.reigndemoapp.data.model.HitIdEntity
import cl.orlandoormazabal.reigndemoapp.extensions.toHitEntity

class RepoImp(
    private val networkState: NetworkState,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val hitIdDao: HitIdDao,
    private val hitDao: HitDao
    ): Repo {

    override suspend fun getHits(): List<Hit> {
         if (networkState.isInternetAvailable().isInternetAvailable) {
            hitDao.deletePreviousHits()
            val newHitList = remoteDataSource.getRemoteHits()
            newHitList.map { it.toHitEntity() }.forEach { entity ->
                hitDao.insertHit(entity)
            }
        }
        return localDataSource.getLocalHits(hitDao)
    }

    override suspend fun insertDeleteHitId(id: String) {
        hitIdDao.insert(HitIdEntity(0, id))
    }
}