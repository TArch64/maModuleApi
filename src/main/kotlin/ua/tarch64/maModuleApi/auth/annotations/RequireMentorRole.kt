package ua.tarch64.maModuleApi.auth.annotations

import org.springframework.security.access.prepost.PreAuthorize

@PreAuthorize("hasAuthority('MENTOR')")
annotation class RequireMentorRole
