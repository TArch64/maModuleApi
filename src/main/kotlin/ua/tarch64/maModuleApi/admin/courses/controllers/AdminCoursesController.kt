package ua.tarch64.maModuleApi.admin.courses.controllers

import org.springframework.web.bind.annotation.*
import ua.tarch64.maModuleApi.admin.courses.CoursesFacade
import ua.tarch64.maModuleApi.admin.courses.controllers.requests.AddCourseRequest
import ua.tarch64.maModuleApi.admin.courses.controllers.responses.CourseResponse
import ua.tarch64.maModuleApi.admin.courses.controllers.responses.FullCourseResponse
import ua.tarch64.maModuleApi.auth.annotations.RequireAdminRole
import java.util.*
import javax.validation.Valid

@RestController
@RequireAdminRole
@RequestMapping("/admin")
class AdminCoursesController(private val coursesFacade: CoursesFacade) {
    @GetMapping("/seasons/{season_id}/courses")
    fun getCourses(@PathVariable("season_id") seasonId: UUID): List<CourseResponse> {
        return coursesFacade.getCourses(seasonId).map(CourseResponse::fromEntity)
    }

    @PostMapping("/seasons/{season_id}/courses")
    fun addCourse(
        @PathVariable("season_id") seasonId: UUID,
        @Valid @RequestBody body: AddCourseRequest
    ): CourseResponse {
        val course = coursesFacade.addCourse(seasonId, body.name, body.type)
        return CourseResponse.fromEntity(course)
    }

    @GetMapping("/courses/{course_id}")
    fun getCourseById(@PathVariable("course_id") courseId: UUID): FullCourseResponse? {
        return coursesFacade.getCourseById(courseId)?.let(FullCourseResponse::fromEntity)
    }

    @DeleteMapping("/courses/{course_id}")
    fun deleteCourse(@PathVariable("course_id") courseId: UUID) {
        coursesFacade.deleteCourse(courseId)
    }
}
