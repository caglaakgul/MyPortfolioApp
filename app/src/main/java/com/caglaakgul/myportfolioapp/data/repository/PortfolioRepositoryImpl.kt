package com.caglaakgul.myportfolioapp.data.repository

import com.caglaakgul.myportfolioapp.data.local.dao.PortfolioDao
import com.caglaakgul.myportfolioapp.data.local.entity.*
import com.caglaakgul.myportfolioapp.data.mapper.toDomain
import com.caglaakgul.myportfolioapp.data.remote.PortfolioApi
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
        val remote = api.getPortfolio(tokenParam)

        val local = PortfolioLocal(
            id = 0,
            about = AboutLocal(
                name = remote.about.name,
                title = remote.about.title,
                location = remote.about.location,
                email = remote.about.email,
                phone = remote.about.phone,
                headline = remote.about.headline,
                github = remote.about.links.github,
                linkedin = remote.about.links.linkedin,
                medium = remote.about.links.medium
            ),
            experiences = remote.experiences.map {
                ExperienceLocal(
                    company = it.company,
                    role = it.role,
                    location = it.location,
                    period = it.period,
                    summary = it.summary,
                    techStack = it.techStack
                )
            },
            education = remote.education.map {
                EducationLocal(
                    school = it.school,
                    degree = it.degree,
                    location = it.location,
                    period = it.period,
                    summary = it.summary
                )
            },
            techStack = remote.techStack.categories.map {
                TechStackCategoryLocal(
                    category = it.category,
                    items = it.items
                )
            },
            projects = remote.projects.map {
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

        dao.clearPortfolio()
        dao.insertPortfolio(local)
    }
}