package com.dev.lokeshkalal.cache.mapper

interface CacheMapper<C, E> {


    fun mapFromcached(type: C): E

    fun mapToCached(type: E): C

}