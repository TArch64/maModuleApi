package ua.tarch64.maModuleApi.courses.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ua.tarch64.maModuleApi.courses.entities.CourseStudentEntity
import java.util.*

@Repository
interface CourseStudentsRepository: JpaRepository<CourseStudentEntity, UUID>
