package com.dev.lokeshkalal.data.mapper

interface EntityMapper<E, D> {


    fun mapFromEntity(entity: E): D

    fun mapToEntity(domain: D): E
}