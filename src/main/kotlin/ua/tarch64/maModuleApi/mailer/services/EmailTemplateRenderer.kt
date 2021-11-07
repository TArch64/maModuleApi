package ua.tarch64.maModuleApi.mailer.services

import freemarker.template.Template
import org.springframework.stereotype.Service
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer

@Service
class EmailTemplateRenderer(private val freemarkerConfigurer: FreeMarkerConfigurer) {
    fun render(name: String, payload: Map<String, String>): String {
        return FreeMarkerTemplateUtils.processTemplateIntoString(getTemplate(name), payload)
    }

    private fun getTemplate(name: String): Template {
        return freemarkerConfigurer.configuration.getTemplate("$name.ftl")
    }
}
