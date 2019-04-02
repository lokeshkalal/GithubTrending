package com.dev.lokeshkalal.ui.bookmarked

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.lokeshkalal.presentation.browse.BrowseBookmarkProjectViewModel
import com.dev.lokeshkalal.presentation.mapper.ProjectVieweMapper
import com.dev.lokeshkalal.presentation.model.ProjectView
import com.dev.lokeshkalal.presentation.state.Resource
import com.dev.lokeshkalal.presentation.state.ResourceState
import com.dev.lokeshkalal.ui.R
import com.dev.lokeshkalal.ui.injection.ViewModelFactory
import com.dev.lokeshkalal.ui.mapper.ProjectViewMapper
import com.dev.lokeshkalal.ui.model.Project
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_bookmarked.*
import javax.inject.Inject

class BookmarkedActivity : AppCompatActivity() {

    @Inject
    lateinit var mapper: ProjectViewMapper

    lateinit var viewModel: BrowseBookmarkProjectViewModel
    @Inject
    lateinit var adapter: BookmarkedAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, BookmarkedActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmarked)
        AndroidInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BrowseBookmarkProjectViewModel::class.java)
        setUpRecylcerView()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getProjects().observe(this, Observer {
            it?.let { handleDataState(it) }
        })
        viewModel.fetchProjects()
    }

    private fun setUpRecylcerView() {
        recycler_projects.layoutManager = LinearLayoutManager(this)
    }

    private fun setupScreenForSuccess(projects: List<Project>?) {
        progress.visibility = View.GONE
        projects?.let {
            adapter.projects = it
            adapter.notifyDataSetChanged()
            recycler_projects.visibility = View.VISIBLE
        } ?: run {

        }
    }

    private fun handleDataState(resource: Resource<List<ProjectView>>) {

        when (resource.resourceState) {

            ResourceState.SUCCESS -> {
                progress.visibility = View.GONE
                recycler_projects.visibility = View.VISIBLE
                resource.data?.let {
                    adapter.projects = it.map { mapper.mapToView(it) }
                    adapter.notifyDataSetChanged()
                }

            }

            ResourceState.LOADING -> {
                recycler_projects.visibility = View.GONE
                progress.visibility = View.VISIBLE
            }
            ResourceState.ERROR -> {
            }
        }
    }
}