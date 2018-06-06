package es.cervecitas.earthquakeobserver.domain.executor.threads;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;
import es.cervecitas.earthquakeobserver.domain.executor.ExecutionThread;
import es.cervecitas.earthquakeobserver.domain.executor.PostExecutionThread;

/**
 * Provides executor dependencies
 */
@Module
public abstract class ThreadsModule {

    @Binds
    @Reusable
    abstract ExecutionThread executionThread(IOExecutionThread ioExecutionThread);

    @Binds
    @Reusable
    abstract PostExecutionThread postExecutionThread(MainPostExecutionThread mainPostExecutionThread);
}
