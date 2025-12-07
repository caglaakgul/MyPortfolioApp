package com.caglaakgul.myportfolioapp.data.mapper

import com.caglaakgul.myportfolioapp.data.local.entity.*
import com.caglaakgul.myportfolioapp.domain.model.*

fun PortfolioLocal.toDomain(): Portfolio {
    return Portfolio(
        about = about.toDomain(),
        experiences = experiences.map { it.toDomain() },
        education = education.map { it.toDomain() },
        techStack = techStack.map { it.toDomain() },
        projects = projects.map { it.toDomain() }
    )
}

fun AboutLocal.toDomain(): About = About(
    name = name,
    title = title,
    location = location,
    email = email,
    phone = phone,
    headline = headline,
    github = github,
    linkedin = linkedin,
    medium = medium
)

fun ExperienceLocal.toDomain(): Experience = Experience(
    company = company,
    role = role,
    location = location,
    period = period,
    summary = summary,
    techStack = techStack
)

fun EducationLocal.toDomain(): Education = Education(
    school = school,
    degree = degree,
    location = location,
    period = period,
    summary = summary
)

fun TechStackCategoryLocal.toDomain(): TechStackCategory = TechStackCategory(
    category = category,
    items = items
)

fun ProjectLocal.toDomain(): Project {
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
        category = categoryEnum,
        company = company
    )
}