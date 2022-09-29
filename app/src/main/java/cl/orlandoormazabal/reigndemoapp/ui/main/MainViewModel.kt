package cl.orlandoormazabal.reigndemoapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.orlandoormazabal.reigndemoapp.base.resource.Resource
import cl.orlandoormazabal.reigndemoapp.data.model.Hit
import cl.orlandoormazabal.reigndemoapp.domain.Repo
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repo: Repo): ViewModel() {

    val hitList = MutableLiveData<Resource<List<Hit>>?>()

    fun getHitList() {
        if (hitList.value == null) {
            viewModelScope.launch {
                hitList.value = Resource.Loading()
                try {
                    hitList.value = Resource.Success(repo.getHits())
                } catch (e: Exception) {

                }
            }
        }
    }

    fun insertDeleteHitId(id: String) {
        viewModelScope.launch {
            repo.insertDeleteHitId(id)
        }
    }

    fun clearData() {
        hitList.value = null
    }
}