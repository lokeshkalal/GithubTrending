package com.dev.lokeshkalal.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dev.lokeshkalal.cache.db.ConfigConstants

@Entity(tableName = ConfigConstants.TABLE_NAME)
class Config(
    @PrimaryKey(autoGenerate = true)
    var id: Int = -1,
    val lastCachedTime: Long
) {
}