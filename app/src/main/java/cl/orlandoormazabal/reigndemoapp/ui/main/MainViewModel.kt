package cl.orlandoormazabal.reigndemoapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.orlandoormazabal.reigndemoapp.base.resource.Resource
import cl.orlandoormazabal.reigndemoapp.data.model.Hit
import cl.orlandoormazabal.reigndemoapp.domain.Repo
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repo: Repo): ViewModel() {

    private val _hitList = MutableLiveData<Resource<List<Hit>>>()
    val hitList: LiveData<Resource<List<Hit>>>
        get() = _hitList

    fun getHitList() {
        if(hitList.value == null) {
            viewModelScope.launch {
                _hitList.value = Resource.Loading()
                try {
                    _hitList.value = Resource.Success(repo.getHits())
                } catch (e: Exception) {

                }
            }
        }
    }
}