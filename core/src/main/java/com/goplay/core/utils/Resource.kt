package com.goplay.core.utils

enum class Status {
    SUCCESS,
    LOADING,
    ERROR
}

data class Resource<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null,
    val uri: String? = null,
) {
    companion object {
        fun <T> success(data: T?, url: String?): Resource<T> {
            return Resource(status = Status.SUCCESS, data = data, uri = url)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(status = Status.LOADING, data = data)
        }

        fun <T> error(msg: String?, data: T?): Resource<T> {
            return Resource(status = Status.ERROR, data = data, message = msg)
        }
    }
}