package com.caglaakgul.myportfolioapp.domain.model

data class About(
    val name: String,
    val title: String,
    val location: String,
    val email: String,
    val phone: String,
    val headline: String,
    val github: String?,
    val linkedin: String?,
    val medium: String?
)