package com.dev.lokeshkalal.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dev.lokeshkalal.cache.db.ProjectConstants

@Entity(tableName = ProjectConstants.TABLE_NAME)
class CachedProject(
    @PrimaryKey
    @ColumnInfo(name = ProjectConstants.COLUMN_PROJECT_ID)
    var id: String,
    var name: String,
    var fullName: String,
    val starCount: String,
    var dateCreated: String,
    var ownerName: String,
    var ownerAvatar: String,
    @ColumnInfo(name = ProjectConstants.COLUMN_IS_BOOKMARKED)
    var isBookmarked: Boolean
) {
}