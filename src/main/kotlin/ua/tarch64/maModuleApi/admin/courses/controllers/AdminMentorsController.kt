package ua.tarch64.maModuleApi.admin.courses.controllers

import org.springframework.web.bind.annotation.*
import ua.tarch64.maModuleApi.admin.courses.CoursesFacade
import ua.tarch64.maModuleApi.admin.courses.controllers.requests.AddCourseMembersRequest
import ua.tarch64.maModuleApi.admin.courses.controllers.requests.ChangeLeadMentorRequest
import ua.tarch64.maModuleApi.admin.courses.controllers.responses.CourseMentorResponse
import ua.tarch64.maModuleApi.auth.annotations.RequireAdminRole
import java.util.*
import javax.validation.Valid

@RestController
@RequireAdminRole
@RequestMapping("/admin")
class AdminMentorsController(private val coursesFacade: CoursesFacade) {
    @PostMapping("/courses/{course_id}/mentors")
    fun addMentors(
        @PathVariable("course_id") courseId: UUID,
        @Valid @RequestBody body: AddCourseMembersRequest
    ): List<CourseMentorResponse> {
        return coursesFacade.addMentors(courseId, body.emails).map(CourseMentorResponse::fromEntity)
    }

    @DeleteMapping("/users/mentors/{mentor_id}")
    fun removeMentor(@PathVariable("mentor_id") courseId: UUID) {
        return coursesFacade.removeMentorFromCourse(courseId)
    }

    @PostMapping("/courses/{course_id}/lead-mentor")
    fun changeLeadMentor(
        @PathVariable("course_id") courseId: UUID,
        @Valid @RequestBody body: ChangeLeadMentorRequest
    ) {
        coursesFacade.changeLeadMentor(courseId, body.mentorId)
    }
}
