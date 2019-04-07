package com.dev.lokeshkalal.ui.injection

import android.app.Application
import com.dev.lokeshkalal.domain.repository.ProjectsRepository
import com.dev.lokeshkalal.ui.injection.module.*
import com.dev.lokeshkalal.ui.test.TestApplication
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidInjectionModule::class,
        TestApplicationModule::class,
        TestCacheModule::class,
        TestDataModule::class,
        PresentationModule::class,
        UiModule::class,
        TestRemoteModule::class
    )
)
interface TestApplicationComponent {

    fun projectRepository(): ProjectsRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): TestApplicationComponent.Builder

        fun build(): TestApplicationComponent
    }

    fun inject(application: TestApplication)

}