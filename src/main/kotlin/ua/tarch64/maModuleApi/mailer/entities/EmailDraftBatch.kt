package ua.tarch64.maModuleApi.mailer.entities

data class EmailDraftBatch(
    val subject: String,
    val template: String,
    val items: List<Item>
) {
    data class Item(
        val to: String,
        val payload: Map<String, String> = emptyMap()
    )

    fun asList(): List<EmailDraft> {
        return items.map {
            EmailDraft(
                to = it.to,
                subject = subject,
                template = template,
                payload = it.payload
            )
        }
    }
}
