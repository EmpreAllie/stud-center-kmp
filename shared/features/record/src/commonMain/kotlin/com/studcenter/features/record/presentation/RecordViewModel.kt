package com.studcenter.features.record.presentation

import com.studcenter.base.features.StateFlow
import com.studcenter.base.features.ViewModel
import com.studcenter.base.features.enum.Screen
import com.studcenter.base.features.enum.StateScreen
import com.studcenter.data.model.CustomResponseException
import com.studcenter.data.utils.localize
import com.studcenter.features.record.domain.RecordRepository
import com.studcenter.resources.MultiplatformResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecordViewModel(
    private val recordRepository: RecordRepository
): ViewModel() {
    val errorText: StateFlow<String?> = StateFlow(null)
    val isCreatedRecord: StateFlow<Boolean> = StateFlow(false)

    public fun createRecord(firstName: String, lastName: String, middleName: String, group: String) {
        viewModelScope.launch {
            try {
                updateStateScreen(StateScreen.LOADING)

                withContext(Dispatchers.IO) {
                    recordRepository.createRecord(
                        firstName = firstName,
                        lastName = lastName,
                        middleName = middleName,
                        group = group
                    )
                }

                isCreatedRecord.update(true)
                updateStateScreen(StateScreen.DEFAULT)
            }
            catch (e: CustomResponseException) {
                e.printStackTrace()

                updateStateScreen(StateScreen.DEFAULT)
                errorText.update(e.responseMessage)
            }
            catch (e: Throwable) {
                e.printStackTrace()

                val message = e.message

                if (message.isNullOrBlank()) {
                    errorText.update(MultiplatformResource.strings.errorDescription_Template.localize())
                } else {
                    errorText.update(message)
                }

                updateStateScreen(StateScreen.DEFAULT)

            }
        }
    }


    public fun clearErrorText() {
        errorText.update(null)
    }
}