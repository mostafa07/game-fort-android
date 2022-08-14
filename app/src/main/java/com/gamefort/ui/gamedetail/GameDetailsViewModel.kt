package com.gamefort.ui.gamedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamefort.R
import com.gamefort.data.model.app.CustomMessage
import com.gamefort.data.model.domain.GameDetails
import com.gamefort.data.model.source.remote.gamedetails.GameDetailsApiResponseResult
import com.gamefort.data.repository.GameDetailsRepository
import com.gamefort.exception.BusinessException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class GameDetailsViewModel : ViewModel() {

    private val gameDetailsRepository: GameDetailsRepository = GameDetailsRepository()

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private val _gameDetails: MutableLiveData<GameDetails?> = MutableLiveData(null)
    val gameDetails: LiveData<GameDetails?>
        get() = _gameDetails


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


    fun fetchGameDetails(gameId: Int) {
        viewModelScope.launch {
            showLoading()
            gameDetailsRepository.getGameDetailsStream(gameId).collect {
                when (it) {
                    is GameDetailsApiResponseResult.Success -> {
                        _gameDetails.postValue(it.data)
                    }
                    is GameDetailsApiResponseResult.Error -> {
                        setErrorMessage(it.exception)
                    }
                }
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