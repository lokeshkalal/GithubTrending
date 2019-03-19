package com.dev.lokeshkalal.domain.interactor.bookmark

import com.dev.lokeshkalal.domain.executor.PostExecutionThread
import com.dev.lokeshkalal.domain.interactor.bookmarked.getBookmarkedProjects
import com.dev.lokeshkalal.domain.model.Project
import com.dev.lokeshkalal.domain.repository.ProjectsRepository
import com.dev.lokeshkalal.domain.test.ProjectDataFactory
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetBookMarkProjectTest {

    private lateinit var getBookmarkedProjects: getBookmarkedProjects
    @Mock
    lateinit var projectsRepository: ProjectsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBookmarkedProjects = getBookmarkedProjects(projectsRepository, postExecutionThread)
    }


    @Test
    fun getBookmarkedProjectsCompletes() {
        stubGetProject(Observable.just(ProjectDataFactory.makeProjectList(10)))
        val testObserver = getBookmarkedProjects.buildUseCaseobservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkedProjectsReturns() {
        val projects = ProjectDataFactory.makeProjectList(10)
        stubGetProject(Observable.just(projects))
        val testObserver = getBookmarkedProjects.buildUseCaseobservable().test()
        testObserver.assertValue(projects)
    }

    private fun stubGetProject(observable: Observable<List<Project>>) {
        whenever(projectsRepository.getBookMarkedProjects())
            .thenReturn(observable)

    }
}