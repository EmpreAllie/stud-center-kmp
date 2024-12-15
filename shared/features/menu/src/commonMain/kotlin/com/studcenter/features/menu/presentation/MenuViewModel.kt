package com.studcenter.features.menu.presentation

import com.studcenter.base.features.StateFlow
import com.studcenter.base.features.ViewModel
import com.studcenter.base.features.enum.Screen
import com.studcenter.data.utils.localize
import com.studcenter.features.menu.domain.MenuRepository
import com.studcenter.features.menu.domain.model.TableItem
import com.studcenter.features.menu.domain.model.WorkInfo
import com.studcenter.resources.MultiplatformResource

class MenuViewModel(private val repository: MenuRepository): ViewModel() {
    val newScreen: StateFlow<Screen?> = StateFlow(null)
    val errorText: StateFlow<String?> = StateFlow(null)
    val isWork: StateFlow<Boolean> = StateFlow(false)
    val workInfo: StateFlow<WorkInfo?> = StateFlow(null)
    val tableItems: StateFlow<List<TableItem>> = StateFlow(emptyList())
    val tableFormatted: StateFlow<String> = StateFlow(MultiplatformResource.strings.shiftsClose.toString())

    private var currentTableId: Int = 0

    public fun openShift() {

    }
    public fun closeShift() {

    }

    public fun exitAccount() {

    }

    public fun rememberTableId(id: Int) {
        currentTableId = id
    }

    public fun nextQueue() {

    }

    public fun clearTextError() {
        errorText.update(value = null)
    }

    public fun getTableId() = currentTableId
}