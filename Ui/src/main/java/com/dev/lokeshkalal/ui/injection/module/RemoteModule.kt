package com.dev.lokeshkalal.ui.injection.module

import com.dev.lokeshkalal.data.repository.ProjectsRemote
import com.dev.lokeshkalal.remote.ProjectRemoteImpl
import com.dev.lokeshkalal.remote.service.GithubTrendingService
import com.dev.lokeshkalal.remote.service.GithubTrendingServiceFactory
import com.dev.lokeshkalal.ui.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): GithubTrendingService {
            return GithubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: ProjectRemoteImpl): ProjectsRemote
}