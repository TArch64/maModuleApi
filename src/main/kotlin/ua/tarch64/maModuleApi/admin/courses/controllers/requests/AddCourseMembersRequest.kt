package ua.tarch64.maModuleApi.admin.courses.controllers.requests

import ua.tarch64.maModuleApi.common.Constants
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class AddCourseMembersRequest(
    @NotNull(message = "'mails' can't be blank")
    @Email(message = "Email is not correct", regexp = Constants.Regexps.EMAIL)
    @Size(min = 1, message = "Add at list one email")
    val emails: List<String>
)
