package com.gamefort.data.model.source.remote.gamelist

import com.gamefort.data.model.domain.GameListItem

sealed class GameListApiResponseResult {

    data class Success(val data: List<GameListItem>) : GameListApiResponseResult()
    data class Error(val exception: Exception) : GameListApiResponseResult()
}