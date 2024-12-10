package com.studcenter.entity

class DeadTokenException(
    message: String,
    cause: Throwable
) : RuntimeException(message, cause)