package com.caglaakgul.myportfolioapp.data.remote.dto

data class TechStackCategoryDto(
    val category: String,
    val items: List<String>
)

data class TechStackDto(
    val categories: List<TechStackCategoryDto>
)