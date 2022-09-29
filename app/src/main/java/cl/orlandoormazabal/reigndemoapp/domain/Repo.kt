package cl.orlandoormazabal.reigndemoapp.domain

import cl.orlandoormazabal.reigndemoapp.data.model.Hit

interface Repo {

    suspend fun getHits(): List<Hit>
}