package cl.orlandoormazabal.reigndemoapp.data

import cl.orlandoormazabal.reigndemoapp.data.model.Hit
import cl.orlandoormazabal.reigndemoapp.domain.LocalDataSource

class LocalDataSourceImp: LocalDataSource {

    override fun getLocalHits(): List<Hit> {
        return listOf()
    }
}