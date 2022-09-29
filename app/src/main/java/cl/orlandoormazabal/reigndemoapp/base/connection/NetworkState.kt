package cl.orlandoormazabal.reigndemoapp.base.connection

interface NetworkState {

    fun isInternetAvailable(): Connection
}