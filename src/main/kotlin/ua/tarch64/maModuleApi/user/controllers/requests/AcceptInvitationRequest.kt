package ua.tarch64.maModuleApi.user.controllers.requests

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class AcceptInvitationRequest(
    @NotBlank(message = "'username' can't be blank")
    @Size(min = 5, max = 20, message = "'username' should be longer than 5 symbols and shorten than 20")
    @Pattern.List(
        Pattern(regexp = "^[a-z.]+\$", message = "Allowed symbols in 'username' are English letters (a-z) and dot"),
        Pattern(regexp = "^[^.].+", message = "'username' cannot be started with dot"),
        Pattern(regexp = ".+[^.]\$", message = "'username' cannot be ended with dot")
    )
    val username: String,

    @NotBlank(message = "'password' can't be blank")
    @Size(min = 6, max = 128, message = "'password' should be longer than 6 symbols and shorten than 128")
    val password: String
)
