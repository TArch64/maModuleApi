package ua.tarch64.maModuleApi.asyncJobs.beans

import org.jobrunr.jobs.mappers.JobMapper
import org.jobrunr.storage.StorageProvider
import org.jobrunr.storage.nosql.redis.JedisRedisStorageProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import redis.clients.jedis.JedisPool
import ua.tarch64.maModuleApi.common.services.EnvService


@Configuration
class AsyncJobStorageBean(private val envService: EnvService) {
    @Bean
    fun storageProvider(jobMapper: JobMapper): StorageProvider {
        val jedisPool = JedisPool(envService.redisUrl)
        return JedisRedisStorageProvider(jedisPool).apply { setJobMapper(jobMapper) }
    }
}
