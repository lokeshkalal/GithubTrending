package com.dev.lokeshkalal.domain.interactor.bookmark

import com.dev.lokeshkalal.domain.executor.PostExecutionThread
import com.dev.lokeshkalal.domain.interactor.bookmarked.BookmarkProject
import com.dev.lokeshkalal.domain.repository.ProjectsRepository
import com.dev.lokeshkalal.domain.test.ProjectDataFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class BookmarkProjectTest {

    private lateinit var bookmarkProject: BookmarkProject
    @Mock
    lateinit var projectsRepository: ProjectsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        bookmarkProject = BookmarkProject(projectsRepository, postExecutionThread)
    }


    @Test
    fun bookmarkProjectCompletes() {
        stubBookMarkProject(Completable.complete())
        val testObserver =
            bookmarkProject.buildUseCaseCompletable(BookmarkProject.Params(ProjectDataFactory.randomUuid())).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkProjectThrowsException() {
        bookmarkProject.buildUseCaseCompletable().test()
    }

    private fun stubBookMarkProject(completable: Completable) {
        whenever(projectsRepository.bookmarkProject(any())).thenReturn(completable)
    }
}