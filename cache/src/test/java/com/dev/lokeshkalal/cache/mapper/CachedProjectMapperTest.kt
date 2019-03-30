package com.dev.lokeshkalal.cache.mapper

import com.dev.lokeshkalal.cache.factory.ProjectDataFactory
import com.dev.lokeshkalal.cache.model.CachedProject
import com.dev.lokeshkalal.data.model.ProjectEntity
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class CachedProjectMapperTest {

    private val mapper = CachedProjectMapper()
    @Test
    fun mapFromCachedMapsData() {
        val model = ProjectDataFactory.makeCachedProject()
        val entity = mapper.mapFromcached(model)

        assertEqualData(model, entity)
    }

    @Test
    fun mapToCachedMapsData() {
        val entity = ProjectDataFactory.makeProjectEntity()
        val model = mapper.mapToCached(entity)

        assertEqualData(model, entity)
    }

    private fun assertEqualData(
        model: CachedProject,
        entity: ProjectEntity
    ) {
        assertEquals(model.id, entity.id)
        assertEquals(model.fullName, entity.fullName)
        assertEquals(model.name, entity.name)
        assertEquals(model.dateCreated, entity.dateCreated)
        assertEquals(model.starCount, entity.startCount)
        assertEquals(model.isBookmarked, entity.isBookMarked)
        assertEquals(model.ownerName, entity.ownerName)
        assertEquals(model.ownerAvatar, entity.ownerAvatar)
    }
}