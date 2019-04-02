package com.dev.lokeshkalal.ui

import android.app.Activity
import android.app.Application
import com.dev.lokeshkalal.ui.injection.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class GithubTrendingApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()
        setUpTimber()
        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)

    }

    private fun setUpTimber() {
        Timber.plant(Timber.DebugTree())
    }
}