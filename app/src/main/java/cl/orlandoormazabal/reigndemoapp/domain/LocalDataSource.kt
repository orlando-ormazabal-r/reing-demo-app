package cl.orlandoormazabal.reigndemoapp.domain

import cl.orlandoormazabal.reigndemoapp.data.model.Hit

interface LocalDataSource {

    suspend fun getLocalHits(hitDao: HitDao): List<Hit>
}