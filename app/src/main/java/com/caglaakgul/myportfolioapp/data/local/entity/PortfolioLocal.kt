package com.caglaakgul.myportfolioapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "portfolio")
data class PortfolioLocal(
    @PrimaryKey val id: Int = 0,
    val about: AboutLocal,
    val experiences: List<ExperienceLocal>,
    val education: List<EducationLocal>,
    val techStack: List<TechStackCategoryLocal>,
    val projects: List<ProjectLocal>
)