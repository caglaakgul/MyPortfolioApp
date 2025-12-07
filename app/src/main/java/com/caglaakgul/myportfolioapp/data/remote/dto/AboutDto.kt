package com.caglaakgul.myportfolioapp.data.remote.dto

data class AboutDto(
    val name: String,
    val title: String,
    val location: String,
    val email: String,
    val phone: String,
    val headline: String,
    val links: AboutLinksDto
)

data class AboutLinksDto(
    val github: String?,
    val linkedin: String?,
    val medium: String?
)