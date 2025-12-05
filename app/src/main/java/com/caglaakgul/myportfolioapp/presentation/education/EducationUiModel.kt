package com.caglaakgul.myportfolioapp.presentation.education

data class EducationUiModel(
    val school: String,
    val degree: String,
    val location: String,
    val period: String,
    val summary: String? = null
)