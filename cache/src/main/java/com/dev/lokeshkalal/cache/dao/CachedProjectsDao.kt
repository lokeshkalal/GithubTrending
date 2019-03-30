package com.dev.lokeshkalal.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dev.lokeshkalal.cache.db.ProjectConstants
import com.dev.lokeshkalal.cache.db.ProjectConstants.DELETE_PROJECTS
import com.dev.lokeshkalal.cache.db.ProjectConstants.QUERY_BOOKMARKED_PROJECTS
import com.dev.lokeshkalal.cache.db.ProjectConstants.QUERY_UPDATE_BOOKMARK_STATUS
import com.dev.lokeshkalal.cache.model.CachedProject
import io.reactivex.Flowable

@Dao
abstract class CachedProjectsDao {

    @Query(ProjectConstants.QUERY_PROJECTS)
    abstract fun getProjects(): Flowable<List<CachedProject>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProjects(projects: List<CachedProject>)


    @Query(QUERY_BOOKMARKED_PROJECTS)
    abstract fun getBookmarkedProjects(): Flowable<List<CachedProject>>

    @Query(QUERY_UPDATE_BOOKMARK_STATUS)
    abstract fun setBookmarkStatus(
        isBookmarked: Boolean,
        projectId: String
    )

    @Query(DELETE_PROJECTS)
    abstract fun deleteProjects()
}