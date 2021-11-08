package ua.tarch64.maModuleApi.mailer.services

import org.springframework.core.io.ClassPathResource
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.common.Constants
import ua.tarch64.maModuleApi.mailer.entities.EmailDraft
import javax.mail.internet.MimeMessage

@Service
class EmailSender(
    private val sender: JavaMailSender,
    private val renderer: EmailTemplateRenderer
) {
    fun send(draft: EmailDraft) {
        sender.send(createMessage(draft))
    }

    fun send(drafts: List<EmailDraft>) {
        sender.send(*drafts.map(this::createMessage).toTypedArray())
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
