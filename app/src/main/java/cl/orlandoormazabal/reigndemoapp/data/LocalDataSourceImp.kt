package cl.orlandoormazabal.reigndemoapp.data

import cl.orlandoormazabal.reigndemoapp.data.model.Hit
import cl.orlandoormazabal.reigndemoapp.domain.HitDao
import cl.orlandoormazabal.reigndemoapp.domain.LocalDataSource
import cl.orlandoormazabal.reigndemoapp.extensions.toHit

class LocalDataSourceImp: LocalDataSource {

    override suspend fun getLocalHits(hitDao: HitDao): List<Hit> {
        return hitDao.getHits().map { it.toHit() }
    }
}