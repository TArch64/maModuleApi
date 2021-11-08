package ua.tarch64.maModuleApi.mailer.entities

data class EmailTemplate(
    val name: String,
    val payload: Map<String, String> = emptyMap(),
    val images: List<String> = emptyList()
) {
    fun usePayload(vararg data: Pair<String, String>): EmailTemplate {
        return copy(payload = mapOf(*data))
    }
}
