package com.studcenter.features.display.presentation

import com.studcenter.base.features.StateFlow
import com.studcenter.base.features.ViewModel
import com.studcenter.data.model.CustomResponseException
import com.studcenter.data.utils.format
import com.studcenter.data.utils.localize
import com.studcenter.features.display.domain.DisplayRepository
import com.studcenter.resources.MultiplatformResource
import io.ktor.http.isSuccess
import kotlinx.coroutines.launch

class DisplayViewModel(private val repository: DisplayRepository): ViewModel() {
    val isCloseScreen: StateFlow<Boolean> = StateFlow(false)
    val errorText: StateFlow<String?> = StateFlow(null)

    val positionFormatted: StateFlow<String> = StateFlow(MultiplatformResource.strings.yourPosition.localize() + ": -")
    val statusFormatted: StateFlow<String> = StateFlow("-")

    init {
        start()
    }

    public fun clearErrorText() {
        start()
        errorText.update(value = null)
    }

    private fun start() {
        viewModelScope.launch {
            try {
                repository.start(
                    onPositionFormatted = { position ->
                        positionFormatted.update(value = position)
                    },
                    onStatusFormatted = { status ->
                        statusFormatted.update(value = status)
                    }
                )
            } catch (e: CustomResponseException) {
                e.printStackTrace()
                stop()

                if (e.response.status.isSuccess()) {
                    isCloseScreen.update(true)
                } else {
                    errorText.update(e.message)
                }
            } catch (e: Throwable) {
                e.printStackTrace()
                stop()

                isCloseScreen.update(true)
            }
        }
    }

    public fun stop() {
        repository.stop()
    }
}