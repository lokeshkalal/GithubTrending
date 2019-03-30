package com.dev.lokeshkalal.remote.mapper

interface ModelMapper<M, E> {

    fun mapFromModel(model: M): E

}