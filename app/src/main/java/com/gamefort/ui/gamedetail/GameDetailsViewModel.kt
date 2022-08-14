package com.gamefort.ui.gamedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamefort.R
import com.gamefort.data.model.app.CustomMessage
import com.gamefort.data.model.domain.GameDetails
import com.gamefort.data.model.domain.GameListItem
import com.gamefort.data.model.source.remote.gamelist.GameListApiResponseResult
import com.gamefort.data.repository.GameRepository
import com.gamefort.exception.BusinessException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class GameDetailsViewModel: ViewModel() {

    private val gameRepository: GameRepository = GameRepository()

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _games: MutableLiveData<List<GameListItem>> = MutableLiveData(null)
    val games: LiveData<List<GameListItem>>
        get() = _games

    // FIXME
    private val _selectedGameDetails: MutableLiveData<GameDetails?> = MutableLiveData(null)
    val selectedGameDetails: LiveData<GameDetails?>
        get() = _selectedGameDetails


    private val _successMessage: MutableLiveData<CustomMessage> = MutableLiveData()
    val successMessage: LiveData<CustomMessage>
        get() = _successMessage

    private val _errorMessage: MutableLiveData<CustomMessage> = MutableLiveData()
    val errorMessage: LiveData<CustomMessage>
        get() = _errorMessage

    private val _isContentLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isContentLoading: LiveData<Boolean>
        get() = _isContentLoading


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    // FIXME
    fun fetchGames() {
        viewModelScope.launch {
            showLoading()
//            try {
            val response = gameRepository.getGamesStream().collect {
                when (it) {
                    is GameListApiResponseResult.Success -> {
                        _games.postValue(it.data)
//                            _successMessage.postValue()
//                            setSuccessMessage(R.string.)
                    }
                    is GameListApiResponseResult.Error -> {
//                            _errorMessage.postValue(CustomMessage(R.string.operation_failed))
                        setErrorMessage(it.exception)
                    }
                }
            }
//            } catch (exception: Exception) {
//                _selectedGameDetails.value = null
//                setErrorMessage(exception)
//            }
            hideLoading()
        }
    }

    // FIXME
    fun getGameDetails(id: Int) {
        viewModelScope.launch {
            showLoading()
            try {
//                val response = gameRepository.getGameDetails(id)
//                _selectedGameDetails.postValue(response)
            } catch (exception: Exception) {
                _selectedGameDetails.value = null
                setErrorMessage(exception)
            }
            hideLoading()
        }
    }


    private fun setSuccessMessage(message: CustomMessage) {
        _successMessage.value = message
    }

    private fun setErrorMessage(errorMessage: CustomMessage) {
        _errorMessage.value = errorMessage
    }

    private fun setErrorMessage(t: Throwable) {
        if (t is BusinessException) {
            setErrorMessage(t.businessMessage)
        } else {
            t.printStackTrace()
            setErrorMessage(CustomMessage(R.string.operation_failed))
        }
    }

    private fun showLoading() {
        _isContentLoading.value = true
    }

    private fun hideLoading() {
        _isContentLoading.value = false
    }
}