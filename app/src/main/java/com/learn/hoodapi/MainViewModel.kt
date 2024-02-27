package com.learn.hoodapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.hoodapi.models.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
created by Rachit on 12/24/2023.
 */

@HiltViewModel
class MainViewModel @Inject constructor(repository: Repository) : ViewModel() {


    var uiState = MutableStateFlow(UiState())
        private set

    init {
        repository.getData().onEach {
            when (it) {
                is Resource.Error -> {
                    uiState.value = uiState.value.copy(Resource.Error(it.message))
                }

                Resource.Loading -> {
                    uiState.value = uiState.value.copy(Resource.Loading)

                }

                is Resource.Success -> {
                    uiState.value = uiState.value.copy(Resource.Success(it.data))

                }
            }

        }.launchIn(viewModelScope)
    }
}


data class UiState(val items: Resource<List<Character>> = Resource.Loading)

