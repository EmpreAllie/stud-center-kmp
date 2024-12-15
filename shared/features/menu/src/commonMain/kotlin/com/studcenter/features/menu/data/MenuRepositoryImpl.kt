package com.studcenter.features.menu.data

import com.studcenter.data.model.ConfigParams
import com.studcenter.data.utils.Log
import com.studcenter.data.utils.localize
import com.studcenter.features.menu.domain.MenuRepository
import com.studcenter.features.menu.domain.model.TableItem
import com.studcenter.features.menu.domain.model.WorkInfo
import com.studcenter.resources.MultiplatformResource
import dev.icerock.moko.network.generated.apis.NotificationApi
import dev.icerock.moko.network.generated.apis.QueueApi
import dev.icerock.moko.network.generated.apis.ShiftsApi
import dev.icerock.moko.network.generated.apis.TablesApi
import dev.icerock.moko.network.generated.models.ShiftOpenRequest

class MenuRepositoryImpl(
    private val configParams: ConfigParams,
    private val tablesApi: TablesApi,
    private val queueApi: QueueApi,
    private val shiftsApi: ShiftsApi,
    private val notificationApi: NotificationApi
): MenuRepository {
    private var tableId: Int? = null

    override fun rememberTableId(id: Int) {
        tableId = id
    }

    override suspend fun getTableId(): Int? {
        val result = shiftsApi.apiV1EmployeeShiftsGet()

        return result.tableId?.value
    }

    override suspend fun getWorkInfo(): WorkInfo? {
        val couponId = getCurrentCoupon() ?: return null
        val queueCouponResponse = queueApi.apiV1EmployeeQueueCouponGet(couponId = couponId)
        val countQueue = getCountQueue()
        val passNumber = queueCouponResponse.passNumber?.value ?: return null

        return WorkInfo(
            currentNumber = passNumber,
            firstName = queueCouponResponse.firstName ?: "-",
            lastName = queueCouponResponse.lastName ?: "-",
            middleName = queueCouponResponse.middleName?.value,
            group = queueCouponResponse.studentGroup?.value ?: "-",
            inQueueNumber = countQueue
        )
    }

    override suspend fun nextQueue(): Boolean {
        val queue = tablesApi.apiV1EmployeeQueueNextPost()

        return true
    }

    override suspend fun getTables(): List<TableItem> {
        val tables = tablesApi.apiV1EmployeeTablesGet()
        val tableItems: MutableList<TableItem> = mutableListOf()

        tables.forEachIndexed { index, it->
            tableItems.add(
                TableItem(
                    id = it.tableId ?: 0,
                    numberElement = index + 1,
                    employeeFormatted = it.employeeFormatted?.value
                )
            )
        }

        return tableItems
    }

    override suspend fun openShift(): Boolean {
        val id = tableId ?: return false

        val shiftOpenRequest = ShiftOpenRequest(tableId = id)
        val shift = shiftsApi.apiV1EmployeeShiftsOpenPost(shiftOpenRequest = shiftOpenRequest)

        return shift.tableId != null
    }

    override suspend fun closeShift(): Boolean {
        val shift = shiftsApi.apiV1EmployeeShiftsClosePost()

        return shift.success ?: false
    }

    override suspend fun exitAccount(): Boolean {
        val keyValueStorage = configParams.keyValueStorage

        val response = notificationApi.apiV1NotificationFcmTokenDelete()

        return if (response.success == true) {
            keyValueStorage.accessToken = null
            keyValueStorage.refreshToken = null
            true
        } else {
            false
        }
    }

    private suspend fun getCurrentCoupon(): Int? {
        return queueApi.apiV1EmployeeQueueStatusGet().couponId?.value
    }

    private suspend fun getCountQueue(): Int {
        val count = tablesApi.apiV1EmployeeQueueCountGet().coupons

        return count ?: 0
    }

}