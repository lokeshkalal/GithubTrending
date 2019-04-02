package com.dev.lokeshkalal.ui.injection.module

import com.dev.lokeshkalal.domain.executor.PostExecutionThread
import com.dev.lokeshkalal.ui.browse.BrowseActivity
import com.dev.lokeshkalal.ui.UiThread
import com.dev.lokeshkalal.ui.bookmarked.BookmarkedActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {


    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread


    @ContributesAndroidInjector
    abstract fun contributeBrowseActivity(): BrowseActivity

    @ContributesAndroidInjector
    abstract fun contributeBookmarkedActivity(): BookmarkedActivity
}