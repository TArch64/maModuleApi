package ua.tarch64.maModuleApi.mailer.services

import freemarker.template.Template
import org.springframework.stereotype.Service
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer
import ua.tarch64.maModuleApi.mailer.entities.EmailTemplate

@Service
class EmailTemplateRenderer(private val freemarkerConfigurer: FreeMarkerConfigurer) {
    fun render(template: EmailTemplate): String {
        val templateFile = getTemplateFile(template.name)
        return FreeMarkerTemplateUtils.processTemplateIntoString(templateFile, template.payload)
    }

    private fun getTemplateFile(name: String): Template {
        return freemarkerConfigurer.configuration.getTemplate("$name.ftl")
    }
}
