package es.cervecitas.earthquakeobserver.domain.executor;

import io.reactivex.Scheduler;

/**
 * Provides the scheduler for the post execution thread where the use case output is processed.
 * This scheduler is typically the main thread.
 */
public interface PostExecutionThread {

    Scheduler scheduler();
}
