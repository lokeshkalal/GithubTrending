package com.dev.lokeshkalal.domain.interactor.bookmarked

import com.dev.lokeshkalal.domain.executor.PostExecutionThread
import com.dev.lokeshkalal.domain.interactor.CompletableUseCase
import com.dev.lokeshkalal.domain.repository.ProjectsRepository
import io.reactivex.Completable
import java.lang.IllegalArgumentException
import javax.inject.Inject

open class BookmarkProject @Inject constructor(
    val projectsRepository: ProjectsRepository,
    val postExecutionThread: PostExecutionThread
) : CompletableUseCase<BookmarkProject.Params>(postExecutionThread) {
    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null)
            throw  IllegalArgumentException("params cant be null")

        return projectsRepository.bookmarkProject(params.projectId)
    }


    data class Params constructor(val projectId: String) {
        companion object {
            fun forProject(projectId: String): Params {
                return Params(projectId)
            }
        }
    }
}