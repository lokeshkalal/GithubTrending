package com.dev.lokeshkalal.domain.interactor.bookmark

import com.dev.lokeshkalal.domain.executor.PostExecutionThread
import com.dev.lokeshkalal.domain.interactor.bookmarked.BookmarkProject
import com.dev.lokeshkalal.domain.interactor.bookmarked.UnBookmarkProject
import com.dev.lokeshkalal.domain.repository.ProjectsRepository
import com.dev.lokeshkalal.domain.test.ProjectDataFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UnBookmarkProjectTest {

    private lateinit var unBookmarkProject: UnBookmarkProject
    @Mock
    lateinit var projectsRepository: ProjectsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        unBookmarkProject = UnBookmarkProject(projectsRepository, postExecutionThread)
    }


    @Test
    fun unBookmarkProjectCompletes() {
        stubUnBookMarkProject(Completable.complete())
        val testObserver =
            unBookmarkProject.buildUseCaseCompletable(UnBookmarkProject.Params(ProjectDataFactory.randomUuid())).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkProjectThrowsException() {
        unBookmarkProject.buildUseCaseCompletable().test()
    }

    private fun stubUnBookMarkProject(completable: Completable) {
        whenever(projectsRepository.unBookmarkProject(any())).thenReturn(completable)
    }
}