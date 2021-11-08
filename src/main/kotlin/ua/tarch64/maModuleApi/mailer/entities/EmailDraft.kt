package ua.tarch64.maModuleApi.mailer.entities

data class EmailDraft(
    val to: String,
    val subject: String,
    val template: EmailTemplate
)
