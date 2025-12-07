package com.caglaakgul.myportfolioapp.data.local.entity

data class ProjectLocal(
    val id: String,
    val name: String,
    val description: String,
    val techStack: List<String>,
    val playStoreUrl: String?,
    val githubUrl: String?,
    val category: String,
    val company: String?
)