package com.studcenter.features.role.presentation

import com.studcenter.base.features.StateFlow
import com.studcenter.base.features.ViewModel
import com.studcenter.base.features.enum.Screen
import com.studcenter.data.model.CustomResponseException
import com.studcenter.entity.DeadTokenException
import com.studcenter.features.role.domain.RoleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoleViewModel(private val roleRepository: RoleRepository): ViewModel() {
    val newScreen: StateFlow<Screen?> = StateFlow(null)

    public fun onSetScreen(screen: Screen) {
        viewModelScope.launch {
            try {
                if (screen == Screen.RECORD) {
                    val isWait = withContext(Dispatchers.Default) {
                        roleRepository.isWaitQueue()
                    }

                    if (isWait) {
                        newScreen.update(Screen.DISPLAY)
                    } else {
                        newScreen.update(Screen.RECORD)
                    }
                } else {
                    newScreen.update(screen)
                }
            } catch (e: CustomResponseException) {
                e.printStackTrace()
                newScreen.update(Screen.RECORD)
            }
        }
    }
}