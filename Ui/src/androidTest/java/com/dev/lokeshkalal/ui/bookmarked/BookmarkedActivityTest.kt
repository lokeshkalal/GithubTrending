package com.dev.lokeshkalal.ui.bookmarked

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.dev.lokeshkalal.domain.model.Project
import com.dev.lokeshkalal.ui.R
import com.dev.lokeshkalal.ui.factory.ProjectsDataFactory
import com.dev.lokeshkalal.ui.test.TestApplication
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookmarkedActivityTest {


    @Rule
    @JvmField
    val activity = ActivityTestRule<BookmarkedActivity>(BookmarkedActivity::class.java, false, false)



    private fun stubGetRepositoryGetBookmarkedProjects(obervable : Observable<List<Project>>){
        whenever(TestApplication.appComponent().projectRepository().getBookMarkedProjects())
            .thenReturn(obervable)

    }

    @Test
    fun activityLaunches() {
        stubGetRepositoryGetBookmarkedProjects(
            Observable.just(
                listOf(ProjectsDataFactory.makeProject())
            )
        )
        activity.launchActivity(null)
    }

    @Test
    fun bookedmarkedProjectDisplays(){
        val projects = listOf(ProjectsDataFactory.makeProject(),ProjectsDataFactory.makeProject(),ProjectsDataFactory.makeProject())
        stubGetRepositoryGetBookmarkedProjects(Observable.just(projects))
        activity.launchActivity(null)
        projects.forEachIndexed { index, project ->
            Espresso.onView(ViewMatchers.withId(R.id.recycler_projects))
                .perform(RecyclerViewActions.scrollToPosition<BookmarkedAdapter.ViewHolder>(index))
            Espresso.onView(ViewMatchers.withId(R.id.recycler_projects))
                .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(project.fullName))))
        }


    }
}