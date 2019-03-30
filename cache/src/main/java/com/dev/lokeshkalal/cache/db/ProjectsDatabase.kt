package com.dev.lokeshkalal.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dev.lokeshkalal.cache.dao.CachedProjectsDao
import com.dev.lokeshkalal.cache.dao.ConfigDao
import com.dev.lokeshkalal.cache.model.CachedProject
import com.dev.lokeshkalal.cache.model.Config
import javax.inject.Inject

@Database(entities = arrayOf(CachedProject::class, Config::class), version = 1)
abstract class ProjectsDatabase @Inject constructor() : RoomDatabase() {
    abstract fun cachedProjectsDao(): CachedProjectsDao

    abstract fun configDao(): ConfigDao

    companion object {

        private var INSTANCE: ProjectsDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): ProjectsDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            ProjectsDatabase::class.java, "projects.db"
                        )
                            .build()
                    }
                    return INSTANCE as ProjectsDatabase
                }
            }
            return INSTANCE as ProjectsDatabase
        }
    }
}