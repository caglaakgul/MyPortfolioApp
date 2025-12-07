package com.caglaakgul.myportfolioapp.domain.model

data class Portfolio(
    val about: About,
    val experiences: List<Experience>,
    val education: List<Education>,
    val techStack: List<TechStackCategory>,
    val projects: List<Project>
)