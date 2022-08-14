package com.gamefort.webservice

import com.gamefort.BuildConfig
import com.gamefort.data.model.source.remote.gamedetails.GameDetailsApiResponse
import com.gamefort.data.model.source.remote.gamelist.GameListApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameWebService {

    @GET(GAMES_END_POINT)
    suspend fun getGames(
        @Query(API_KEY_QUERY_PARAM) apiKey: String = BuildConfig.RAWG_API_KEY,
        @Query(PAGE_QUERY_PARAM) page: Int,
        @Query(PAGE_SIZE_QUERY_PARAM) pageSize: Int
    ): GameListApiResponse

    @GET("$GAMES_END_POINT/{id}")
    suspend fun getGameDetails(
        @Path(ID_PATH_PARAM) id: Int,
        @Query(API_KEY_QUERY_PARAM) apiKey: String = BuildConfig.RAWG_API_KEY
    ): GameDetailsApiResponse


    companion object {
        const val GAMES_END_POINT = "games"

        const val API_KEY_QUERY_PARAM = "key"

        const val PAGE_QUERY_PARAM = "page"
        const val PAGE_SIZE_QUERY_PARAM = "page_size"

        const val ID_PATH_PARAM = "id"
    }
}