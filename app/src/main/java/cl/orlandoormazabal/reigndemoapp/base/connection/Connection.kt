package cl.orlandoormazabal.reigndemoapp.base.connection

data class Connection(
    val state: ConnectionState,
    val type: ConnectionType,
    val isInternetAvailable: Boolean
)

enum class ConnectionState(val value: String) {
    CONNECTED("Available"),
    DISCONNECTED("Unavailable")
}

enum class ConnectionType{
    WIFI,
    MOBILE,
    NETWORK_NO_CONNECTION
}