package com.goplay.home.ui.viewmodel

import Type
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.goplay.core.network.data.model.result.People
import com.goplay.core.network.data.repository.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val repositoryImpl: RepositoryImpl
) : ViewModel() {

    private val _peoplePopular: MutableLiveData<Flow<PagingData<People>>> = MutableLiveData()

    val peoplePopular: MutableLiveData<Flow<PagingData<People>>>
        get() = _peoplePopular

    init {
        fetchPopularPeople()
    }

    private fun fetchPopularPeople(type: String = Type.POPULAR) {
        viewModelScope.launch {
            val peoplePopular = repositoryImpl.fetchPeople(type).cachedIn(viewModelScope)
            _peoplePopular.postValue(peoplePopular)
        }
    }
}