package ua.tarch64.maModuleApi.mailer.asyncJobs

import org.jobrunr.jobs.lambdas.JobRequest
import org.jobrunr.jobs.lambdas.JobRequestHandler
import ua.tarch64.maModuleApi.mailer.entities.EmailDraft

data class SendEmailJobRequest(
    val emailDrafts: List<EmailDraft>
): JobRequest {
    constructor(emailDraft: EmailDraft) : this(listOf(emailDraft))

    override fun getJobRequestHandler(): Class<out JobRequestHandler<SendEmailJobRequest>> {
        return SendEmailJobHandler::class.java
    }
}
