package com.goplay.home.ui.viewmodel

import Type
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goplay.core.network.data.model.result.PeopleResponse
import com.goplay.core.network.data.repository.RepositoryImpl
import com.goplay.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val repositoryImpl: RepositoryImpl
) : ViewModel() {

    private val _peoplePopular: MutableLiveData<Flow<Resource<PeopleResponse>>> = MutableLiveData()

    val peoplePopular: LiveData<Flow<Resource<PeopleResponse>>>
        get() = _peoplePopular

    init {
        fetchPopularPeople()
    }

    private fun fetchPopularPeople() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                repositoryImpl.fetchPeople(Type.POPULAR).let {
                    _peoplePopular.postValue(it)
                }
            }
        }
    }
}