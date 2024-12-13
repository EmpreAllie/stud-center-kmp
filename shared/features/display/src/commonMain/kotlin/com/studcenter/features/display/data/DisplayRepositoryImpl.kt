package com.studcenter.features.display.data

import com.studcenter.data.model.ConfigParams
import com.studcenter.data.model.CustomResponseException
import com.studcenter.data.utils.CustomExceptionParser
import com.studcenter.data.utils.localize
import com.studcenter.domain.constants.Constants
import com.studcenter.features.display.domain.DisplayRepository
import com.studcenter.resources.MultiplatformResource
import dev.icerock.moko.network.generated.apis.StudentApi
import io.ktor.client.statement.request
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DisplayRepositoryImpl(private val configParams: ConfigParams, private val studentApi: StudentApi): DisplayRepository {
    private var job: Job? = null

    override fun start(onPositionFormatted: (String) -> Unit, onStatusFormatted: (String) -> Unit) {
        job?.cancel()
        job = CoroutineScope(Dispatchers.IO).launch {
            while (job?.isActive == true) {
                val response = studentApi.apiV1StudentQueueGetResponse(
                    deviceId = configParams.configAppProvider.deviceId
                )

                val body = response.body()
                val passNumber = body.passNumber?.value
                val status = body.status?.value

                if (passNumber == null) {
                    stop()

                    throw CustomResponseException(
                        request = response.httpResponse.request,
                        response = response.httpResponse,
                        responseStatus = response.httpResponse.status.value,
                        responseMessage = MultiplatformResource.strings.status.localize(),
                    )
                }


                val positionText = MultiplatformResource.strings.yourPosition.localize() + ":\n${passNumber}"

                withContext(Dispatchers.Main) {
                    onPositionFormatted(positionText)
                    onStatusFormatted(status ?: "")
                }
            }
            delay(Constants.Numbers.displayUpdateMs)
        }
    }

    override fun stop() {
        if (job?.isActive == true) {
            job?.cancel()
            job = null
        }
    }
}