package es.cervecitas.earthquakeobserver.domain.executor.threads;

import javax.inject.Inject;

import dagger.Reusable;
import es.cervecitas.earthquakeobserver.domain.executor.ExecutionThread;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * An {@link ExecutionThread} that provides the {@link Schedulers#io()} scheduler.
 */
@Reusable
final class IOExecutionThread implements ExecutionThread {

    @Inject
    IOExecutionThread() {

    }

    @Override
    public Scheduler scheduler() {
        return Schedulers.io();
    }
}
