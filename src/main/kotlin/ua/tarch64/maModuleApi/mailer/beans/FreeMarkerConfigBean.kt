package ua.tarch64.maModuleApi.mailer.beans

import freemarker.cache.ClassTemplateLoader
import freemarker.template.Configuration as FreeMarkerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer

@Configuration
class FreeMarkerConfigBean {
    private val LOADER_CLASS =  FreeMarkerConfigBean::class.java
    private val TEMPLATE_PATH = "/emails"

    @Bean
    fun freemarkerClassLoaderConfig(): FreeMarkerConfigurer {
        return FreeMarkerConfigurer().apply {
            configuration = FreeMarkerConfig().apply {
                templateLoader = ClassTemplateLoader(LOADER_CLASS, TEMPLATE_PATH)
            }
        }
    }
}
