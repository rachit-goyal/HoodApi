package com.learn.hoodapi

import com.learn.hoodapi.models.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
created by Rachit on 12/24/2023.
 */
interface Repository {
    fun getData(): Flow<Resource<List<Character>>>
}

class RepoImpl @Inject constructor(private val apiService: ApiService) : Repository {
    override fun getData(): Flow<Resource<List<Character>>> = flow {
        emit(Resource.Loading)
        try {
            val data = apiService.getList()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))

        }
    }.flowOn(Dispatchers.IO)


}


