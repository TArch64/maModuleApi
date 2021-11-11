package ua.tarch64.maModuleApi.mailer.asyncJobs

import org.jobrunr.jobs.lambdas.JobRequestHandler
import org.springframework.core.io.ClassPathResource
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import ua.tarch64.maModuleApi.common.Constants
import ua.tarch64.maModuleApi.mailer.entities.EmailDraft
import ua.tarch64.maModuleApi.mailer.services.EmailTemplateRenderer
import javax.mail.internet.MimeMessage

@Component
class SendEmailJobHandler(
    private val sender: JavaMailSender,
    private val renderer: EmailTemplateRenderer,
): JobRequestHandler<SendEmailJobRequest> {
    override fun run(jobRequest: SendEmailJobRequest) {
        val messages = jobRequest.emailDrafts.map(this::createMessage)
        sender.send(*messages.toTypedArray())
    }

    private fun createMessage(draft: EmailDraft): MimeMessage {
        val body = renderer.render(draft.template)

        return sender.createMimeMessage().apply {
            val isMultipart = draft.template.images.isNotEmpty()
            val helper = MimeMessageHelper(this, isMultipart,"utf-8")

            helper.setFrom(Constants.Mail.FROM)
            helper.setTo(draft.to)
            helper.setSubject(draft.subject)
            helper.setText(body, true)

            if (isMultipart) {
                draft.template.images.forEach { defineImage(helper, it) }
            }
        }
    }

    private fun defineImage(helper: MimeMessageHelper, path: String) {
        helper.addInline(path, ClassPathResource("emails/images/$path"))
    }
}
