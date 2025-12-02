package com.caglaakgul.myportfolioapp.data.remote

import com.caglaakgul.myportfolioapp.data.remote.dto.ProjectDto
import retrofit2.http.GET


interface ProjectApi {

    @GET("projects")
    suspend fun getProjects(): List<ProjectDto>
}