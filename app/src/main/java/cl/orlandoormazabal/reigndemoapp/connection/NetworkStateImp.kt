package cl.orlandoormazabal.reigndemoapp.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkStateImp(private val context: Context): NetworkState {

    override fun isInternetAvailable(): Connection {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        return hasTransport(connectivityManager.getNetworkCapabilities(activeNetwork))
    }

    private fun hasTransport(networkCapabilities: NetworkCapabilities?): Connection =
        when {
            networkCapabilities == null ->
                Connection(ConnectionState.DISCONNECTED, ConnectionType.NETWORK_NO_CONNECTION, false)

            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ->
                Connection(ConnectionState.CONNECTED, ConnectionType.MOBILE, true)

            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->
                Connection(ConnectionState.CONNECTED, ConnectionType.WIFI, true)

            else -> Connection(ConnectionState.DISCONNECTED, ConnectionType.NETWORK_NO_CONNECTION, false)
        }
}

enum class ConnectionState(val value: String) {
    CONNECTED("Available"),
    DISCONNECTED("Unavailable")
}

enum class ConnectionType{
    WIFI,
    MOBILE,
    NETWORK_NO_CONNECTION
}

data class Connection(
    val state: ConnectionState,
    val type: ConnectionType,
    val isInternetAvailable: Boolean
)