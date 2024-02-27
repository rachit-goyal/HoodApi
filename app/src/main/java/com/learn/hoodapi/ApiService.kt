package com.learn.hoodapi

import com.learn.hoodapi.models.Character
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

/**
created by Rachit on 12/24/2023.
 */
interface  ApiService {

    @GET(ApiConstants.API_END_POINT)
    suspend fun getList():List<Character>

}