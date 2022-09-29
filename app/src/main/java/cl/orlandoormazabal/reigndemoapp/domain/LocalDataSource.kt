package cl.orlandoormazabal.reigndemoapp.domain

import cl.orlandoormazabal.reigndemoapp.data.model.Hit

interface LocalDataSource {

    fun getLocalHits(): List<Hit>
}