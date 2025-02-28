package com.studcenter.base.features.enum

enum class Screen {
    SPLASH {
        override fun toString() = "splash"
    },
    AUTHORIZATION {
        override fun toString() = "authorization"
    };

    abstract override fun toString(): String
}