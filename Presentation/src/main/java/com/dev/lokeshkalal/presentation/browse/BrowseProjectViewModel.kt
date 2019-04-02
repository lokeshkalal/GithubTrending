package com.dev.lokeshkalal.presentation.browse

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.lokeshkalal.domain.interactor.bookmarked.BookmarkProject
import com.dev.lokeshkalal.domain.interactor.bookmarked.UnBookmarkProject
import com.dev.lokeshkalal.domain.interactor.browse.GetProjects
import com.dev.lokeshkalal.domain.model.Project
import com.dev.lokeshkalal.presentation.mapper.ProjectVieweMapper
import com.dev.lokeshkalal.presentation.model.ProjectView
import com.dev.lokeshkalal.presentation.state.Resource
import com.dev.lokeshkalal.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BrowseProjectViewModel @Inject constructor(
    val getProjects: GetProjects,
    val bookmarkProject: BookmarkProject,
    val unBookmarkProject: UnBookmarkProject,
    val projectVieweMapper: ProjectVieweMapper
) : ViewModel() {
    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData();


    override fun onCleared() {
        getProjects.dispose()
        super.onCleared()
    }

    fun getProjects(): MutableLiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    fun bookmarkProject(projectId: String) {
        liveData.postValue(Resource(ResourceState.LOADING, liveData.value?.data, null))
        bookmarkProject.execute(BookmarkProjectsSubsriber(), BookmarkProject.Params.forProject(projectId))
    }

    fun unBookmarkProject(projectId: String) {
        liveData.postValue(Resource(ResourceState.LOADING,  liveData.value?.data, null))
        unBookmarkProject.execute(UnBookmarkProjectsSubsriber(), UnBookmarkProject.Params.forProject(projectId))
    }

    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getProjects.execute(ProjectsSubsriber())
    }

    inner class ProjectsSubsriber : DisposableObserver<List<Project>>() {
        override fun onComplete() {

        }

        override fun onNext(t: List<Project>) {
            liveData.postValue(Resource(ResourceState.SUCCESS, t.map { projectVieweMapper.mapToView(it) }, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }

    inner class BookmarkProjectsSubsriber : DisposableCompletableObserver() {
        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, liveData.value?.data, e.localizedMessage))
        }


        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data, null))
        }


    }

    inner class UnBookmarkProjectsSubsriber : DisposableCompletableObserver() {

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data, e.localizedMessage))
        }

        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data, null))
        }


    }
}