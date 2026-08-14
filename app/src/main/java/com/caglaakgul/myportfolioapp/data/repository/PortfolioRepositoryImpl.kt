package com.caglaakgul.myportfolioapp.data.repository

import com.caglaakgul.myportfolioapp.data.local.dao.PortfolioDao
import com.caglaakgul.myportfolioapp.data.local.entity.*
import com.caglaakgul.myportfolioapp.data.local.PortfolioSeed
import com.caglaakgul.myportfolioapp.data.mapper.toDomain
import com.caglaakgul.myportfolioapp.data.remote.PortfolioApi
import com.caglaakgul.myportfolioapp.data.remote.dto.PortfolioDto
import com.caglaakgul.myportfolioapp.domain.model.Portfolio
import com.caglaakgul.myportfolioapp.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PortfolioRepositoryImpl @Inject constructor(
    private val api: PortfolioApi,
    private val dao: PortfolioDao,
    private val githubToken: String
) : PortfolioRepository {

    override fun observePortfolio(): Flow<Portfolio?> {
        return dao.observePortfolio()
            .map { local ->
                local?.toDomain()
            }
    }

    override suspend fun refreshPortfolio() {
        val tokenParam = githubToken.takeIf { it.isNotBlank() }
        val local = runCatching {
            api.getPortfolio(tokenParam).toLocal()
        }.getOrElse {
            PortfolioSeed.current
        }

        dao.clearPortfolio()
        dao.insertPortfolio(local)
    }

    private fun PortfolioDto.toLocal(): PortfolioLocal {
        return PortfolioLocal(
            id = 0,
            about = AboutLocal(
                name = about.name,
                title = about.title,
                location = about.location,
                email = about.email,
                phone = about.phone,
                headline = about.headline,
                github = about.links.github,
                linkedin = about.links.linkedin,
                medium = about.links.medium
            ),
            experiences = experiences.map {
                ExperienceLocal(
                    company = it.company,
                    role = it.role,
                    location = it.location,
                    period = it.period,
                    summary = it.summary,
                    techStack = it.techStack,
                    projectUrl = it.projectUrl
                )
            },
            education = education.map {
                EducationLocal(
                    school = it.school,
                    degree = it.degree,
                    location = it.location,
                    period = it.period,
                    summary = it.summary
                )
            },
            techStack = techStack.categories.map {
                TechStackCategoryLocal(
                    category = it.category,
                    items = it.items
                )
            },
            projects = projects.map {
                ProjectLocal(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    techStack = it.techStack,
                    playStoreUrl = it.playStoreUrl,
                    githubUrl = it.githubUrl,
                    category = it.category,
                    company = it.company
                )
            }
        )
    }
}
