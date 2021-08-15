package ua.tarch64.maModuleApi.common.factories

import org.slf4j.Logger
import org.slf4j.LoggerFactory as BaseFactory
import org.springframework.stereotype.Service
import kotlin.reflect.KClass

@Service
class LoggerFactory {
    fun <T: Any>createLogger(target: KClass<T>): Logger {
        return BaseFactory.getLogger(target.java)
    }
}
