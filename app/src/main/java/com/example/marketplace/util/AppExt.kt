package com.example.marketplace.util

fun String?.defaultError(): String {
    return this ?: Constants.DEFAULT_ERROR
}