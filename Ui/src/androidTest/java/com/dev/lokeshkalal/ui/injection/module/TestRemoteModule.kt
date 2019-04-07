package com.dev.lokeshkalal.ui.injection.module

import com.dev.lokeshkalal.data.repository.ProjectsRemote
import com.dev.lokeshkalal.remote.ProjectRemoteImpl
import com.dev.lokeshkalal.remote.service.GithubTrendingService
import com.dev.lokeshkalal.remote.service.GithubTrendingServiceFactory
import com.dev.lokeshkalal.ui.BuildConfig
import com.nhaarman.mockitokotlin2.mock
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
object TestRemoteModule {


    @Provides
    @JvmStatic
    fun provideGithubService(): GithubTrendingService {
        return mock()
    }


    @Provides
    @JvmStatic
    fun bindProjectsRemote(): ProjectsRemote {
        return mock()
    }
}