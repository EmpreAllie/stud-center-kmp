package com.studcenter.features.menu.domain

import com.studcenter.features.menu.domain.model.TableItem
import com.studcenter.features.menu.domain.model.WorkInfo

interface MenuRepository {
    public fun rememberTableId(id: Int)
    public suspend fun getTableId(): Int?
    public suspend fun getWorkInfo(): WorkInfo?
    public suspend fun getTables(): List<TableItem>
    public suspend fun nextQueue(): Boolean
    public suspend fun openShift(): Boolean
    public suspend fun closeShift(): Boolean
    public suspend fun exitAccount(): Boolean
}