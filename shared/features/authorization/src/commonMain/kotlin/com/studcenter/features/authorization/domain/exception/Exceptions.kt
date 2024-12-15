package com.studcenter.features.authorization.domain.exception

class LoginException(override val message: String): Throwable(message = message)
class PasswordException(override val message: String): Throwable(message = message)