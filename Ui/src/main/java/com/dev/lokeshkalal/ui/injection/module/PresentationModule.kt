package com.dev.lokeshkalal.ui.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.lokeshkalal.presentation.browse.BrowseBookmarkProjectViewModel
import com.dev.lokeshkalal.presentation.browse.BrowseProjectViewModel
import com.dev.lokeshkalal.ui.injection.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class PresentationModule {
    @Binds
    @IntoMap
    @ViewModelKey(BrowseProjectViewModel::class)
    abstract fun bindBrowseProjectsViewModel(viewModel: BrowseProjectViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BrowseBookmarkProjectViewModel::class)
    abstract fun bindBrowseBookmarkedProjectsViewModel(
        viewModel: BrowseBookmarkProjectViewModel
    ): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)