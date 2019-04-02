package com.dev.lokeshkalal.presentation.browse

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.lokeshkalal.domain.interactor.bookmarked.getBookmarkedProjects
import com.dev.lokeshkalal.domain.model.Project
import com.dev.lokeshkalal.presentation.mapper.ProjectVieweMapper
import com.dev.lokeshkalal.presentation.model.ProjectView
import com.dev.lokeshkalal.presentation.state.Resource
import com.dev.lokeshkalal.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BrowseBookmarkProjectViewModel @Inject constructor(
    val bookmarkedProjects: getBookmarkedProjects,
    val mapper: ProjectVieweMapper
) : ViewModel() {
    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData();


    fun getProjects(): MutableLiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        bookmarkedProjects.execute(BookmarkProjectSubscriber())
    }

    override fun onCleared() {
        super.onCleared()
        bookmarkedProjects.dispose()
    }

    inner class BookmarkProjectSubscriber : DisposableObserver<List<Project>>() {
        override fun onComplete() {

        }

        override fun onNext(t: List<Project>) {
            liveData.postValue(Resource(ResourceState.SUCCESS, t.map { mapper.mapToView(it) }, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }
}