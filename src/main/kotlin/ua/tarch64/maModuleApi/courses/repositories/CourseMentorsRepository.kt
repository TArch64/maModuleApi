package ua.tarch64.maModuleApi.courses.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.courses.entities.CourseMentorEntity
import ua.tarch64.maModuleApi.courses.enums.CourseMentorRoles
import java.util.*

@Repository
interface CourseMentorsRepository: JpaRepository<CourseMentorEntity, UUID> {
    fun findByCourseAndRole(course: CourseEntity, role: CourseMentorRoles): CourseMentorEntity?
}
