package cl.orlandoormazabal.reigndemoapp.base.resource

sealed class Resource< T> {

    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Failure<T>(val exc: Exception) : Resource<T>()
}