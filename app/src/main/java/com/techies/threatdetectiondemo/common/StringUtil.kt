package com.techies.threatdetectiondemo.common

fun String?.capitalise(): String {
    return this?.split("_")
        ?.joinToString(" ") {
            it.replaceFirstChar { char -> char.uppercase() }
        } ?: "NA"
}