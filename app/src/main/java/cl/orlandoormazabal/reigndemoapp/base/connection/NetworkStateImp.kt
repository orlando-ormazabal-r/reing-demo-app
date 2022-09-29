package cl.orlandoormazabal.reigndemoapp.base.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest

class NetworkStateImp(private val context: Context): NetworkState {

    override fun isInternetAvailable(): Connection {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCallback: ConnectivityManager.NetworkCallback = setNetworkStateCallback()
        val request = NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()
        connectivityManager.registerNetworkCallback(request, networkCallback)
        return hasTransport(connectivityManager.getNetworkCapabilities(activeNetwork))
    }

    private fun setNetworkStateCallback(): ConnectivityManager.NetworkCallback =
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) { }

            override fun onLost(network: Network) { }
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