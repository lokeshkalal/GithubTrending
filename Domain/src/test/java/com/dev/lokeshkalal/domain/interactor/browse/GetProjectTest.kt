package com.dev.lokeshkalal.domain.interactor.browse

import com.dev.lokeshkalal.domain.executor.PostExecutionThread
import com.dev.lokeshkalal.domain.model.Project
import com.dev.lokeshkalal.domain.repository.ProjectsRepository
import com.dev.lokeshkalal.domain.test.ProjectDataFactory
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetProjectTest {

    private lateinit var getProjects: GetProjects
    @Mock
    lateinit var projectsRepository: ProjectsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getProjects = GetProjects(projectsRepository, postExecutionThread)
    }


    @Test
    fun getProjectCompletes() {
        stubGetProject(Observable.just(ProjectDataFactory.makeProjectList(10)))
        val testObserver = getProjects.buildUseCaseobservable().test()
        testObserver.assertComplete()

    }

    @Test
    fun getProjectsReturnsData() {
        val projects = ProjectDataFactory.makeProjectList(2)
        stubGetProject(Observable.just(projects))
        val testObserver = getProjects.buildUseCaseobservable().test()
        testObserver.assertValue(projects)
    }


    private fun stubGetProject(observable: Observable<List<Project>>) {
        whenever(projectsRepository.getProjects())
            .thenReturn(observable)

    }
}