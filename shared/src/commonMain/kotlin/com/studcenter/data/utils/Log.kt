package com.studcenter.data.utils

import com.studcenter.entity.enums.ErrorType

expect fun Log(title: String, message: String, errorType: ErrorType = ErrorType.DISPLAY)
