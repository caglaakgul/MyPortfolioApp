package com.caglaakgul.myportfolioapp.data.local.converter

import androidx.room.TypeConverter
import com.caglaakgul.myportfolioapp.data.local.entity.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PortfolioConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromExperienceList(value: List<ExperienceLocal>): String =
        gson.toJson(value)

    @TypeConverter
    fun toExperienceList(value: String): List<ExperienceLocal> {
        val type = object : TypeToken<List<ExperienceLocal>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromEducationList(value: List<EducationLocal>): String =
        gson.toJson(value)

    @TypeConverter
    fun toEducationList(value: String): List<EducationLocal> {
        val type = object : TypeToken<List<EducationLocal>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromTechStackCategoryList(value: List<TechStackCategoryLocal>): String =
        gson.toJson(value)

    @TypeConverter
    fun toTechStackCategoryList(value: String): List<TechStackCategoryLocal> {
        val type = object : TypeToken<List<TechStackCategoryLocal>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromProjectList(value: List<ProjectLocal>): String =
        gson.toJson(value)

    @TypeConverter
    fun toProjectList(value: String): List<ProjectLocal> {
        val type = object : TypeToken<List<ProjectLocal>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromAbout(value: AboutLocal): String =
        gson.toJson(value)

    @TypeConverter
    fun toAbout(value: String): AboutLocal {
        val type = object : TypeToken<AboutLocal>() {}.type
        return gson.fromJson(value, type)
    }
}
