package com.caglaakgul.myportfolioapp.domain.model

data class Project(
    val id: String,
    val name: String,
    val description: String,
    val techStack: List<String>,
    val imageUrl: String? = null,
    val playStoreUrl: String? = null,
    val githubUrl: String? = null
)