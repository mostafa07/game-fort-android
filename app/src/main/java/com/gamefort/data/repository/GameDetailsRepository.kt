package com.gamefort.data.repository

import com.gamefort.data.model.source.remote.gamedetails.GameDetailsApiResponseResult
import com.gamefort.data.model.source.remote.gamedetails.toDomainModel
import com.gamefort.webservice.GameWebService
import com.gamefort.webservice.builder.RetrofitServiceBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import retrofit2.HttpException
import java.io.IOException

class GameDetailsRepository {

    private val gameWebService: GameWebService =
        RetrofitServiceBuilder.buildService(GameWebService::class.java)


    // shared flow of game details result
    private val gameDetailsResult = MutableSharedFlow<GameDetailsApiResponseResult>(replay = 1)

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false


    suspend fun getGameDetailsStream(gameId: Int): Flow<GameDetailsApiResponseResult> {
        requestData(gameId)
        return gameDetailsResult
    }

    suspend fun retry(gameId: Int) {
        if (isRequestInProgress) return

        requestData(gameId)
    }

    private suspend fun requestData(gameId: Int): Boolean {
        isRequestInProgress = true
        var successful = false

        try {
            val response = gameWebService.getGameDetails(id = gameId)
            val gameDetails = response.toDomainModel()
            gameDetailsResult.emit(GameDetailsApiResponseResult.Success(gameDetails))
            successful = true
        } catch (ioException: IOException) {
            gameDetailsResult.emit(GameDetailsApiResponseResult.Error(ioException))
        } catch (httpException: HttpException) {
            gameDetailsResult.emit(GameDetailsApiResponseResult.Error(httpException))
        }

        isRequestInProgress = false
        return successful
    }
}