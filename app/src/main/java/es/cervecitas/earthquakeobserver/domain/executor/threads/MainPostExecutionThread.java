package es.cervecitas.earthquakeobserver.domain.executor.threads;

import javax.inject.Inject;

import dagger.Reusable;
import es.cervecitas.earthquakeobserver.domain.executor.PostExecutionThread;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * A {@link PostExecutionThread} that provides the {@link AndroidSchedulers#mainThread()} scheduler.
 */
@Reusable
final class MainPostExecutionThread implements PostExecutionThread {

    @Inject
    MainPostExecutionThread() {

    }

    @Override
    public Scheduler scheduler() {
        return AndroidSchedulers.mainThread();
    }
}
