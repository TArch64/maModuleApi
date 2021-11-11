package ua.tarch64.maModuleApi.asyncJobs.services

import org.jobrunr.jobs.Job
import org.jobrunr.jobs.JobId
import org.jobrunr.jobs.lambdas.JobRequest
import org.jobrunr.scheduling.JobRequestScheduler
import org.jobrunr.storage.StorageProvider
import org.springframework.stereotype.Service

@Service
class AsyncJobRunner(
    private val jobScheduler: JobRequestScheduler,
    private val jobStorage: StorageProvider
) {
    fun run(request: JobRequest) {
        jobScheduler.enqueue(request)
    }

    fun run(request: JobRequest, onChange: (job: Job) -> Unit) {
        val jobId = jobScheduler.enqueue(request)
        addChangeListener(jobId, onChange)
    }

    private fun addChangeListener(jobId: JobId, onChange: (job: Job) -> Unit) {
        val listener = BlockJobChangeListener(jobId, onChange)
        jobStorage.addJobStorageOnChangeListener(listener)
    }
}
