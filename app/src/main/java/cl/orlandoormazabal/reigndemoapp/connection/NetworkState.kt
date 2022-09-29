package cl.orlandoormazabal.reigndemoapp.connection

import android.content.Context

interface NetworkState {

    fun isInternetAvailable(): Connection
}