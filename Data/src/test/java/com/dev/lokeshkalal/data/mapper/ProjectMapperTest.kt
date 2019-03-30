package com.dev.lokeshkalal.data.mapper

import com.dev.lokeshkalal.data.model.ProjectEntity
import com.dev.lokeshkalal.data.test.factory.ProjectFactory
import com.dev.lokeshkalal.domain.model.Project
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class ProjectMapperTest {

    private val mapper = ProjectMapper()

    @Test
    fun mapFromEntityMapsData() {
        val entity = ProjectFactory.makeProjectEntity()
        val model = mapper.mapFromEntity(entity)
        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = ProjectFactory.makeProject()
        val entity = mapper.mapToEntity(model)
        assertEqualData(entity, model)
    }

    private fun assertEqualData(
        entity: ProjectEntity,
        model: Project
    ) {
        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.name)
        assertEquals(entity.fullName, model.fullName)
        assertEquals(entity.startCount, model.startCount)
        assertEquals(entity.dateCreated, model.dateCreated)
        assertEquals(entity.ownerName, model.ownerName)
        assertEquals(entity.ownerAvatar, model.ownerAvatar)
        assertEquals(entity.isBookMarked, model.isBookMarked)
    }
}