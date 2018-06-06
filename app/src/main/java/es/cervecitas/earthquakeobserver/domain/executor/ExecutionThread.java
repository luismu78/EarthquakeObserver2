package es.cervecitas.earthquakeobserver.domain.executor;

import io.reactivex.Scheduler;

/**
 * Provides the scheduler for the execution thread where the use case is executed.
 * This scheduler is typically any background thread.
 */
public interface ExecutionThread {

    Scheduler scheduler();
}
