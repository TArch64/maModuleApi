package ua.tarch64.maModuleApi.admin.courses.controllers

import org.springframework.web.bind.annotation.*
import ua.tarch64.maModuleApi.admin.courses.CoursesFacade
import ua.tarch64.maModuleApi.admin.courses.controllers.requests.AddCourseMembersRequest
import ua.tarch64.maModuleApi.admin.courses.controllers.responses.CourseStudentResponse
import ua.tarch64.maModuleApi.auth.annotations.RequireAdminRole
import java.util.*
import javax.validation.Valid

@RestController
@RequireAdminRole
@RequestMapping("/admin")
class AdminStudentsController(private val coursesFacade: CoursesFacade) {
    @PostMapping("/courses/{course_id}/students")
    fun addStudents(
        @PathVariable("course_id") courseId: UUID,
        @Valid @RequestBody body: AddCourseMembersRequest
    ): List<CourseStudentResponse> {
        return coursesFacade.addStudents(courseId, body.emails).map(CourseStudentResponse::fromEntity)
    }
}
