package com.dev.lokeshkalal.domain.model

class Project(
    val id: String, val name: String, val fullName: String,
    val startCount: String, val dateCreated: String, val ownerName: String, val ownerAvatar: String
    , val isBookMarked: Boolean
)