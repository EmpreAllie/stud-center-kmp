package com.studcenter.features.menu.presentation

import com.studcenter.base.features.StateFlow
import com.studcenter.base.features.ViewModel
import com.studcenter.base.features.enum.Screen
import com.studcenter.base.features.enum.StateScreen
import com.studcenter.data.model.CustomResponseException
import com.studcenter.data.utils.format
import com.studcenter.data.utils.localize
import com.studcenter.domain.constants.Constants
import com.studcenter.entity.DeadTokenException
import com.studcenter.features.menu.domain.MenuRepository
import com.studcenter.features.menu.domain.model.TableItem
import com.studcenter.features.menu.domain.model.WorkInfo
import com.studcenter.resources.MultiplatformResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuViewModel(private val repository: MenuRepository): ViewModel() {
    val newScreen: StateFlow<Screen?> = StateFlow(null)
    val errorText: StateFlow<String?> = StateFlow(null)
    val isWork: StateFlow<Boolean> = StateFlow(false)
    val workInfo: StateFlow<WorkInfo?> = StateFlow(null)
    val tableItems: StateFlow<List<TableItem>> = StateFlow(emptyList())
    val tableFormatted: StateFlow<String> = StateFlow(MultiplatformResource.strings.shiftsClose.toString())
    val tableId: StateFlow<Int> = StateFlow(0)

    private var job: Job = Job()

    init {
        update(isLoading = true)
        startPeriodicUpdate()
    }

    override fun onCleared() {
        super.onCleared()
        stopPeriodicUpdate()
    }

    public fun update(isLoading: Boolean = false) {
        viewModelScope.launch {
            try {
                if (isLoading) {
                    updateStateScreen(StateScreen.LOADING)
                }

                val id = withContext(Dispatchers.IO) {
                    repository.getTableId()
                }

                tableId.update(value = id ?: 0)
                isWork.update(value = id != null)

                if (id == null) {
                    updateShiftClosed()
                } else {
                    updateShiftOpened()
                }

            } catch (e: DeadTokenException) {
                e.printStackTrace()
                newScreen.update(Screen.ROLE)
            } catch (e: Throwable) {
                e.printStackTrace()
            }

            updateStateScreen(StateScreen.DEFAULT)
        }
    }

    public fun openShift() {
        viewModelScope.launch {
            updateStateScreen(StateScreen.LOADING)

            try {
                val result = repository.openShift()

            } catch (e: Exception) {
                e.printStackTrace()
            }

            update(isLoading = false)
        }
    }
    public fun closeShift() {
        viewModelScope.launch {
            updateStateScreen(StateScreen.LOADING)

            try {
                val result = repository.closeShift()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            update(isLoading = false)
        }
    }
    public fun exitAccount() {
        viewModelScope.launch {
            updateStateScreen(StateScreen.LOADING)

            try {
                val result = withContext(Dispatchers.IO) {
                    repository.exitAccount()
                }

                if (result) {
                    newScreen.update(Screen.ROLE)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            update(isLoading = false)

            updateStateScreen(StateScreen.DEFAULT)
        }
    }
    public fun rememberTableId(id: Int) {
        repository.rememberTableId(id = id)
    }
    public fun nextQueue() {
        viewModelScope.launch {
            try {
                repository.nextQueue()
            } catch (e: Throwable) {
                e.printStackTrace()
            }

            update(isLoading = false)
        }
    }
    public fun clearTextError() {
        errorText.update(value = null)
    }

    private suspend fun updateShiftClosed() {
        try {
            val items = withContext(Dispatchers.IO) { repository.getTables() }

            tableFormatted.update(MultiplatformResource.strings.shiftsClose.localize())
            tableItems.update(items)
            workInfo.update(null)
        }
        catch (e: DeadTokenException) {
            e.printStackTrace()
            newScreen.update(Screen.ROLE)
        }
        catch (e: CustomResponseException) {
            e.printStackTrace()
            errorText.update(value = e.message)
        }
        catch (e: Throwable) {
            e.printStackTrace()
        }

    }
    private suspend fun updateShiftOpened() {
        try {
            val items = withContext(Dispatchers.IO) { repository.getTables() }

            val currentNumberTable = items.find { it.id == tableId.getValue() }
            val info = withContext(Dispatchers.IO) { repository.getWorkInfo() }

            tableFormatted.update(MultiplatformResource.strings.shiftsOpen.format("${currentNumberTable?.numberElement ?: 0}"))
            tableItems.update(emptyList())
            workInfo.update(info)
        }
        catch (e: DeadTokenException) {
            e.printStackTrace()
            newScreen.update(Screen.ROLE)
        }
        catch (e: CustomResponseException) {
            e.printStackTrace()

            errorText.update(value = e.message)
        }
        catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    private fun startPeriodicUpdate() {
        job = viewModelScope.launch {
            while (isActive) {
                update(isLoading = false)
                withContext(Dispatchers.IO) {
                    delay(Constants.Numbers.menuUpdateMs)
                }
            }
        }
    }
    private fun stopPeriodicUpdate() {
        job.cancel()
    }

}