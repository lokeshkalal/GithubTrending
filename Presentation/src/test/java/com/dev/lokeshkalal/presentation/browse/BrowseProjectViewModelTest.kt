package com.dev.lokeshkalal.presentation.browse

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dev.lokeshkalal.domain.interactor.bookmarked.BookmarkProject
import com.dev.lokeshkalal.domain.interactor.bookmarked.UnBookmarkProject
import com.dev.lokeshkalal.domain.interactor.browse.GetProjects
import com.dev.lokeshkalal.domain.model.Project
import com.dev.lokeshkalal.presentation.factory.DataFactory
import com.dev.lokeshkalal.presentation.factory.ProjectsFactory
import com.dev.lokeshkalal.presentation.mapper.ProjectVieweMapper
import com.dev.lokeshkalal.presentation.model.ProjectView
import com.dev.lokeshkalal.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever

@RunWith(JUnit4::class)
class BrowseProjectViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    var getProjects = mock<GetProjects>()
    var bookmarkProject = mock<BookmarkProject>()
    var unbookmarkProject = mock<UnBookmarkProject>()
    var projectMapper = mock<ProjectVieweMapper>()
    var projectViewModel = BrowseProjectViewModel(
        getProjects,
        bookmarkProject, unbookmarkProject, projectMapper
    )

    @Captor
    val captor = argumentCaptor<DisposableObserver<List<Project>>>()

    @Test
    fun fetchProjectsExecutesUseCase() {
        projectViewModel.fetchProjects()

        verify(getProjects, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetchProjectsReturnsSuccess() {
        val projects = ProjectsFactory.makeProjectList(2)
        val projectViews = ProjectsFactory.makeProjectViewList(2)
        stubProjectMapperMapToView(projectViews[0], projects[0])
        stubProjectMapperMapToView(projectViews[1], projects[1])

        projectViewModel.fetchProjects()

        verify(getProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(projects)

        assertEquals(
            ResourceState.SUCCESS,
            projectViewModel.getProjects().value?.resourceState
        )
    }

    @Test
    fun fetchProjectsReturnsData() {
        val projects = ProjectsFactory.makeProjectList(2)
        val projectViews = ProjectsFactory.makeProjectViewList(2)
        stubProjectMapperMapToView(projectViews[0], projects[0])
        stubProjectMapperMapToView(projectViews[1], projects[1])

        projectViewModel.fetchProjects()

        verify(getProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(projects)

        assertEquals(
            projectViews,
            projectViewModel.getProjects().value?.data
        )
    }

    @Test
    fun fetchProjectsReturnsError() {
        projectViewModel.fetchProjects()

        verify(getProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assertEquals(
            ResourceState.ERROR,
            projectViewModel.getProjects().value?.resourceState
        )
    }

    @Test
    fun fetchProjectsReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()
        projectViewModel.fetchProjects()

        verify(getProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))

        assertEquals(
            errorMessage,
            projectViewModel.getProjects().value?.message
        )
    }

    private fun stubProjectMapperMapToView(
        projectView: ProjectView,
        project: Project
    ) {
        whenever(projectMapper.mapToView(project))
            .thenReturn(projectView)
    }
}