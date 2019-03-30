package com.dev.lokeshkalal.cache.factory

import com.dev.lokeshkalal.cache.model.Config

object ConfigDataFactory {

    fun makeCachedConfig(): Config {

        return Config(DataFactory.randomInt(), DataFactory.randomLong())
    }
}