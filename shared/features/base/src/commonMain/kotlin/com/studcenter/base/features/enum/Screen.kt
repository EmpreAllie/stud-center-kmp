package com.studcenter.base.features.enum

enum class Screen {
    SPLASH {
        override fun toString() = "splash"
    },
    AUTHORIZATION {
        override fun toString() = "authorization"
    },
    DISPLAY {
        override fun toString() = "display"
    },
    MENU {
        override fun toString() = "menu"
    },
    RECORD {
        override fun toString() = "record"
    },
    ROLE {
        override fun toString() = "role"
    };

    abstract override fun toString(): String
}