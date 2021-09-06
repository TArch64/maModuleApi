package ua.tarch64.maModuleApi.admin.courses.controllers

import org.springframework.web.bind.annotation.*
import ua.tarch64.maModuleApi.admin.courses.CoursesFacade
import ua.tarch64.maModuleApi.admin.courses.controllers.requests.AddCourseRequest
import ua.tarch64.maModuleApi.admin.courses.controllers.responses.CourseResponse
import ua.tarch64.maModuleApi.auth.annotations.RequireAdminRole
import javax.validation.Valid

@RestController
@RequireAdminRole
@RequestMapping("/admin")
class CoursesController(private val coursesFacade: CoursesFacade) {
    @GetMapping("/seasons/{season_id}/courses")
    fun getCourses(@PathVariable(name = "season_id") seasonId: Long): List<CourseResponse> {
        return coursesFacade.getCourses(seasonId).map(CourseResponse::fromEntity)
    }

    @PostMapping("/seasons/{season_id}/courses")
    fun addCourse(
        @PathVariable(name = "season_id") seasonId: Long,
        @Valid @RequestBody body: AddCourseRequest
    ): CourseResponse {
        val course = coursesFacade.addCourse(seasonId, body.name, body.type)
        return CourseResponse.fromEntity(course)
    }
}
