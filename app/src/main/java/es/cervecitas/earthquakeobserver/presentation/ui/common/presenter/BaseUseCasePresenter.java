package es.cervecitas.earthquakeobserver.presentation.ui.common.presenter;

import es.cervecitas.earthquakeobserver.domain.executor.UseCaseHandler;
import es.cervecitas.earthquakeobserver.presentation.ui.common.view.MVPView;

/**
 * A {@link BasePresenter} that contains a {@link UseCaseHandler}.
 *
 * @param <T> the type of the {@link MVPView}.
 */

public abstract class BaseUseCasePresenter<T extends MVPView> extends BasePresenter<T> {

    protected final UseCaseHandler useCaseHandler;

    protected BaseUseCasePresenter(T view, UseCaseHandler useCaseHandler) {
        super(view);
        this.useCaseHandler = useCaseHandler;
    }

    @Override
    public void onPause() {
        super.onPause();
        clearUseCases();
        // TODO (IMPLEMENTATION) - Pause/resume use cases instead of clearing
    }

    protected void clearUseCases() {
        useCaseHandler.clear();
    }
}
