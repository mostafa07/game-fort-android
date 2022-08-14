package com.gamefort.data.model.source.remote.gamedetails

import com.gamefort.data.model.domain.GameDetails

sealed class GameDetailsApiResponseResult {

    data class Success(val data: GameDetails) : GameDetailsApiResponseResult()
    data class Error(val exception: Exception) : GameDetailsApiResponseResult()
}