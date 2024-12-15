package com.studcenter.domain.constants

object Constants {

    object Numbers {
        val SECOND = 1000L
        val MINUTE = SECOND * 60
        val HOUR = SECOND * 60

        val delaySplash = SECOND * 2
        val displayUpdateMs = SECOND * 5
        val menuUpdateMs = SECOND * 10

        val passNumberNull = 401
        val maxLenghtField = 16
        val notification = SECOND * 10
    }

    object Strings {
        val errorConnection: String = "Нет подключения к интернету"

        object SYSTEM {
            val mailSupport: String = "studcenter@voenmeh.ru"
        }

        object NOTIFICATION {
            val idChannel = "0"
            val nameChannel = "StudyCenter"
        }
    }

    object Currency {

    }
    object Region {
    }

    object RequestCode {
    }

}