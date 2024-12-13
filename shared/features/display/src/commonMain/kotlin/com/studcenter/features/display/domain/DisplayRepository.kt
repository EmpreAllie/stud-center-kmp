package com.studcenter.features.display.domain

interface DisplayRepository {
    public fun start(onPositionFormatted: (String) -> Unit, onStatusFormatted: (String) -> Unit)
    public fun stop()
}