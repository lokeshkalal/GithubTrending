package com.dev.lokeshkalal.ui.browse

import com.dev.lokeshkalal.ui.model.Project

interface ProjectListener {

    fun onBookmarkProjectClicked(projectId: String)

    fun onProjectClicked(projectId: String)
}