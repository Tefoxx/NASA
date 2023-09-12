package com.ent21.nasa.api.model

enum class MediaType(val title: String, val value: Int) {
    UNDEFINED("undefined", 0),
    IMAGE("image", 1),
    VIDEO("video", 2);

    companion object {
        fun getByValue(value: Int) = values().find { it.value == value } ?: UNDEFINED

        fun getByTitle(title: String) = values().find { it.title == title } ?: UNDEFINED
    }
}