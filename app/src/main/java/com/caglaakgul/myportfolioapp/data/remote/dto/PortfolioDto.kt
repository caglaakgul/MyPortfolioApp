package com.caglaakgul.myportfolioapp.data.remote.dto

data class PortfolioDto(
    val projects: List<ProjectDto>,
    val about: AboutDto,
    val experiences: List<ExperienceDto>,
    val education: List<EducationDto>,
    val techStack: TechStackDto
)