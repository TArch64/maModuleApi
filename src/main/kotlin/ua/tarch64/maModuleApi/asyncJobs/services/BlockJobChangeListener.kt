package ua.tarch64.maModuleApi.asyncJobs.services

import org.jobrunr.jobs.Job
import org.jobrunr.jobs.JobId
import org.jobrunr.storage.listeners.JobChangeListener

class BlockJobChangeListener(
    private val jobId: JobId,
    private val onChangeBlock: (job: Job) -> Unit,
): JobChangeListener {
    override fun close() {}
    override fun getJobId(): JobId = jobId
    override fun onChange(job: Job) = onChangeBlock(job)
}
