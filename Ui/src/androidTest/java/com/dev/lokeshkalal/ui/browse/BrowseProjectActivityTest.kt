package com.dev.lokeshkalal.ui.browse

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.dev.lokeshkalal.domain.model.Project
import com.dev.lokeshkalal.ui.R
import com.dev.lokeshkalal.ui.factory.ProjectsDataFactory
import com.dev.lokeshkalal.ui.factory.TestProjectFactory
import com.dev.lokeshkalal.ui.test.TestApplication
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_browse.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BrowseProjectActivityTest {

    @Rule @JvmField
    val activity = ActivityTestRule<BrowseActivity>(BrowseActivity::class.java, false, false)

    @Test
    fun activityLaunches() {
        stubProjectsRepositoryGetProjects(
            Observable.just(
                listOf(ProjectsDataFactory.makeProject())
            )
        )
        activity.launchActivity(null)
    }

    @Test
    fun projectDisplays(){
        val projects = listOf(ProjectsDataFactory.makeProject(),ProjectsDataFactory.makeProject(),ProjectsDataFactory.makeProject())
        stubProjectsRepositoryGetProjects(Observable.just(projects))
        activity.launchActivity(null)
        projects.forEachIndexed { index, project ->
            Espresso.onView(withId(R.id.recycler_projects))
                .perform(RecyclerViewActions.scrollToPosition<BrowseAdapter.ViewHolder>(index))
            Espresso.onView(withId(R.id.recycler_projects))
                .check(matches(hasDescendant(withText(project.fullName))))
        }


    }
    private fun stubProjectsRepositoryGetProjects(observable: Observable<List<Project>>) {
        whenever(TestApplication.appComponent().projectRepository().getProjects())
            .thenReturn(observable)
    }
}