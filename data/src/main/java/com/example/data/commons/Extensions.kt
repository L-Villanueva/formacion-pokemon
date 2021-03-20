package com.example.data.commons


fun String.clean (): String {
    return this.drop(19)
}
fun String.clean2 (): String {

    return split("offset=").last()
}
