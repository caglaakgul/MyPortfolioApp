package com.caglaakgul.myportfolioapp.data.mapper

import com.caglaakgul.myportfolioapp.data.remote.dto.ProjectDto
import com.caglaakgul.myportfolioapp.domain.model.Project
import com.caglaakgul.myportfolioapp.domain.model.ProjectCategory

fun ProjectDto.toDomain(): Project {
    val categoryEnum = when (category.lowercase()) {
        "personal" -> ProjectCategory.PERSONAL
        "freelance" -> ProjectCategory.FREELANCE
        "professional" -> ProjectCategory.PROFESSIONAL
        else -> ProjectCategory.PERSONAL
    }

    return Project(
        id = id,
        name = name,
        description = description,
        techStack = techStack,
        playStoreUrl = playStoreUrl,
        githubUrl = githubUrl,
        category = categoryEnum
    )
}
