package es.cervecitas.earthquakeobserver.domain.executor;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import es.cervecitas.earthquakeobserver.domain.interactors.UseCase;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Provides default handling of use cases.
 * <p>
 * This handler executes a use cases in the given {@link ExecutionThread} and then publishes results
 * in the given {@link PostExecutionThread}.
 */
public final class UseCaseHandler {

    private final ExecutionThread executionThread;
    private final PostExecutionThread postExecutionThread;
    private final CompositeDisposable disposables;

    @Nullable
    private UseCase previousUseCase;

    @Nullable
    private Object previousUseCaseParams;

    @Inject
    UseCaseHandler(ExecutionThread executionThread,
                   PostExecutionThread postExecutionThread,
                   CompositeDisposable disposables) {
        this.executionThread = executionThread;
        this.postExecutionThread = postExecutionThread;
        this.disposables = disposables;
    }

    public <K, V> void execute(UseCase<K, V> useCase,
                               @Nullable K params,
                               DisposableObserver<V> observer) {
        setLastUseCase(useCase, params);
        Disposable disposable = useCase.execute(params)
                .subscribeOn(executionThread.scheduler())
                .observeOn(postExecutionThread.scheduler())
                .subscribeWith(observer);
        disposables.add(disposable);
    }

    public <K, V> void execute(UseCase<K, V> useCase, DisposableObserver<V> observer) {
        execute(useCase, null, observer);
    }

    public void executePreviousUseCase(DisposableObserver observer) {
        if (previousUseCase != null) {
            execute(previousUseCase, previousUseCaseParams, observer);
        }
    }

    public void clear() {
        // clear only and not dispose the composite to enable composite reuse
        disposables.clear();
    }

    private void setLastUseCase(UseCase previousUseCase, @Nullable Object previousUseCaseParams) {
        this.previousUseCase = previousUseCase;
        this.previousUseCaseParams = previousUseCaseParams;
    }
}
