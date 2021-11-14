package ua.tarch64.maModuleApi.asyncJobs.services

import org.jobrunr.jobs.lambdas.JobRequest
import org.jobrunr.scheduling.JobRequestScheduler
import org.springframework.stereotype.Service

@Service
class AsyncJobRunner(private val jobScheduler: JobRequestScheduler) {
    fun run(request: JobRequest) {
        jobScheduler.enqueue(request)
    }
}
