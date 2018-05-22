package es.cervecitas.earthquakeobserver.domain.executor;

import dagger.Module;
import dagger.Provides;
import es.cervecitas.earthquakeobserver.domain.executor.threads.ThreadsModule;
import io.reactivex.disposables.CompositeDisposable;

@Module(includes = {
        ThreadsModule.class
})
public final class ExecutorModule {

    @Provides
    CompositeDisposable compositeDisposable() {
        return new CompositeDisposable();
    }
}
