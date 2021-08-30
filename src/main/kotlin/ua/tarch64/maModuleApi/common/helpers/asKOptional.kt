package ua.tarch64.maModuleApi.common.helpers

import java.util.*

fun <T> Optional<T>.asKOptional(): T? {
    return try { get() } catch (exception: NoSuchElementException) { null }
}
