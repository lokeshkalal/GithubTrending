package com.dev.lokeshkalal.presentation.mapper

import com.dev.lokeshkalal.presentation.factory.ProjectsFactory
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectVieweMapperTest{
    private val mapper = ProjectVieweMapper()

    @Test
    fun mapToViewMapsData() {
        val project = ProjectsFactory.makeProject()
        val projectView = mapper.mapToView(project)

        assertEquals(project.id, projectView.id)
        assertEquals(project.name, projectView.name)
        assertEquals(project.fullName, projectView.fullName)
        assertEquals(project.startCount, projectView.starCount)
        assertEquals(project.dateCreated, projectView.dateCreated)
        assertEquals(project.ownerName, projectView.ownerName)
        assertEquals(project.ownerAvatar, projectView.ownerAvatar)
        assertEquals(project.isBookMarked, projectView.isBookmarked)
    }
}