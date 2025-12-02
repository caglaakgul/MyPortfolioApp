package com.caglaakgul.myportfolioapp.data.mapper

import com.caglaakgul.myportfolioapp.data.remote.dto.ProjectDto
import com.caglaakgul.myportfolioapp.domain.model.Project

fun ProjectDto.toDomain(): Project {
    return Project(
        id = id,
        name = name,
        description = description,
        techStack = techStack,
        imageUrl = imageUrl,
        playStoreUrl = playStoreUrl,
        githubUrl = githubUrl
    )
}