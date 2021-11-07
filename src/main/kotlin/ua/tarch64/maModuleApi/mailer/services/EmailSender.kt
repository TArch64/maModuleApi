package ua.tarch64.maModuleApi.mailer.services

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.common.Constants
import ua.tarch64.maModuleApi.mailer.entities.EmailDraft
import ua.tarch64.maModuleApi.mailer.entities.EmailDraftBatch
import javax.mail.internet.MimeMessage

@Service
class EmailSender(
    private val sender: JavaMailSender,
    private val renderer: EmailTemplateRenderer
) {
    fun send(draft: EmailDraft) {
        sender.send(createMessage(draft))
    }

    fun send(batch: EmailDraftBatch) {
        val messages = batch.asList().map(this::createMessage)
        sender.send(*messages.toTypedArray())
    }

    private fun createMessage(template: EmailDraft): MimeMessage {
        val body = renderer.render(template.template, template.payload)

        return sender.createMimeMessage().apply {
            val helper = MimeMessageHelper(this, "utf-8")
            helper.setFrom(Constants.Mail.FROM)
            helper.setTo(template.to)
            helper.setSubject(template.subject)
            helper.setText(body, true)
        }
    }
}
