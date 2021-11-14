package ua.tarch64.maModuleApi.mailer.services

import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.asyncJobs.services.AsyncJobRunner
import ua.tarch64.maModuleApi.mailer.asyncJobs.SendEmailJobRequest
import ua.tarch64.maModuleApi.mailer.entities.EmailDraft

@Service
class EmailSender(private val asyncJobRunner: AsyncJobRunner) {
    fun send(draft: EmailDraft) {
        asyncJobRunner.run(SendEmailJobRequest(draft))
    }

    fun send(drafts: List<EmailDraft>) {
        asyncJobRunner.run(SendEmailJobRequest(drafts))
    }
}
