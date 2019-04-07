package com.dev.lokeshkalal.ui.browse

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.lokeshkalal.presentation.browse.BrowseProjectViewModel
import com.dev.lokeshkalal.presentation.model.ProjectView
import com.dev.lokeshkalal.presentation.state.Resource
import com.dev.lokeshkalal.presentation.state.ResourceState
import com.dev.lokeshkalal.ui.R
import com.dev.lokeshkalal.ui.bookmarked.BookmarkedActivity
import com.dev.lokeshkalal.ui.injection.ViewModelFactory
import com.dev.lokeshkalal.ui.mapper.ProjectViewMapper
import com.dev.lokeshkalal.ui.model.Project
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_browse.*
import javax.inject.Inject

class BrowseActivity : AppCompatActivity() {

    @Inject
    lateinit var browseAdapter: BrowseAdapter
    @Inject
    lateinit var mapper: ProjectViewMapper
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var browseViewModel: BrowseProjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        AndroidInjection.inject(this)
        browseViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(BrowseProjectViewModel::class.java)

        setUpBrowsRecyclerView()
    }



    override fun onStart() {
        super.onStart()
        browseViewModel.getProjects().observe(this,
            Observer<Resource<List<ProjectView>>> {
                it?.let {
                    handleDataState(it)
                }
            })
        browseViewModel.fetchProjects()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
            R.id.action_bookmarked -> {
                startActivity(BookmarkedActivity.getStartIntent(this))
                return true
            }
            else -> {
                return false
            }
        }
    }

    fun setUpBrowsRecyclerView() {
        browseAdapter.projectListener = projectListener
        recycler_projects.layoutManager = LinearLayoutManager(this)
        recycler_projects.adapter = browseAdapter
    }

    private fun handleDataState(resource: Resource<List<ProjectView>>) {
        when (resource.resourceState) {
            ResourceState.SUCCESS -> {
                setupScreenForSuccess(resource.data?.map {
                    mapper.mapToView(it)
                })
            }
            ResourceState.LOADING -> {
                progress.visibility = View.VISIBLE
                recycler_projects.visibility = View.GONE
            }
        }
    }

    private fun setupScreenForSuccess(projects: List<Project>?) {
        progress.visibility = View.GONE
        projects?.let {
            browseAdapter.projects = it
            browseAdapter.notifyDataSetChanged()
            recycler_projects.visibility = View.VISIBLE
        } ?: run {

        }
    }

    private val projectListener = object : ProjectListener {
        override fun onBookmarkProjectClicked(projectId: String) {
            browseViewModel.unBookmarkProject(projectId)
        }

        override fun onProjectClicked(projectId: String) {
            browseViewModel.bookmarkProject(projectId)
        }
    }
}