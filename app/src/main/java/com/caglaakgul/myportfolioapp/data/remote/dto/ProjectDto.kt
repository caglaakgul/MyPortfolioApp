package com.caglaakgul.myportfolioapp.data.remote.dto

data class ProjectDto(
    val id: String,
    val name: String,
    val description: String,
    val techStack: List<String>,
    val imageUrl: String?,
    val playStoreUrl: String?,
    val githubUrl: String?
)