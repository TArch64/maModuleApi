package ua.tarch64.maModuleApi.mailer.services

import org.jobrunr.jobs.Job
import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.asyncJobs.services.AsyncJobRunner
import ua.tarch64.maModuleApi.mailer.asyncJobs.SendEmailJobRequest
import ua.tarch64.maModuleApi.mailer.entities.EmailDraft

@Service
class EmailSender(private val asyncJobRunner: AsyncJobRunner) {
    fun send(draft: EmailDraft) {
        asyncJobRunner.run(SendEmailJobRequest(draft))
    }

    fun send(draft: EmailDraft, onSent: (job: Job) -> Unit) {
        asyncJobRunner.run(SendEmailJobRequest(draft), onSent)
    }

    fun send(drafts: List<EmailDraft>) {
        asyncJobRunner.run(SendEmailJobRequest(drafts))
    }

    fun send(drafts: List<EmailDraft>, onSent: (job: Job) -> Unit) {
        asyncJobRunner.run(SendEmailJobRequest(drafts), onSent)
    }
}
