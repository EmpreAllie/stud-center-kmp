package com.studcenter.features.role.domain

interface RoleRepository {
    public suspend fun isWaitQueue(): Boolean
}