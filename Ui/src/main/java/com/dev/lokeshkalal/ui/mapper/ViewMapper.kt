package com.dev.lokeshkalal.ui.mapper

interface ViewMapper<P, V> {

    fun mapToView(presentation: P): V
}