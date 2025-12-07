package com.caglaakgul.myportfolioapp.data.remote

import com.caglaakgul.myportfolioapp.data.remote.dto.PortfolioDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PortfolioApi {
    @GET("portfolio.json")
    suspend fun getPortfolio(
        @Query("token") token: String? = null
    ): PortfolioDto
}