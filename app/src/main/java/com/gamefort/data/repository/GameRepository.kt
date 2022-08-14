package com.gamefort.data.repository

import com.gamefort.data.model.source.remote.gamelist.GameListApiResponseResult
import com.gamefort.data.model.source.remote.gamelist.toDomainModel
import com.gamefort.webservice.GameWebService
import com.gamefort.webservice.builder.RetrofitServiceBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import retrofit2.HttpException
import java.io.IOException

private const val RAWG_STARTING_PAGE_INDEX = 1

class GameRepository {

    private val gameWebService: GameWebService =
        RetrofitServiceBuilder.buildService(GameWebService::class.java)


    // shared flow of results, which allows us to broadcast updates so the subscriber will have the latest data
    private val gameResults = MutableSharedFlow<GameListApiResponseResult>(replay = 1)

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = RAWG_STARTING_PAGE_INDEX

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false


    suspend fun getGamesStream(): Flow<GameListApiResponseResult> {
        lastRequestedPage = 1
        requestData()
        return gameResults
    }

    suspend fun requestMore() {
        if (isRequestInProgress) return

        val successful = requestData()
        if (successful) {
            lastRequestedPage++
        }
    }

    suspend fun retry() {
        if (isRequestInProgress) return

        requestData()
    }

    private suspend fun requestData(): Boolean {
        isRequestInProgress = true
        var successful = false

        try {
            val response = gameWebService.getGames(
                page = lastRequestedPage,
                pageSize = NETWORK_PAGE_SIZE
            )
            val games = response.toDomainModel() ?: emptyList()
            gameResults.emit(GameListApiResponseResult.Success(games))
            successful = true
        } catch (ioException: IOException) {
            gameResults.emit(GameListApiResponseResult.Error(ioException))
        } catch (httpException: HttpException) {
            gameResults.emit(GameListApiResponseResult.Error(httpException))
        }

        isRequestInProgress = false
        return successful
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}