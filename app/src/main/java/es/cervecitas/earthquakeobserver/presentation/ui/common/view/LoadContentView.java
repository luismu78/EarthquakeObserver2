package es.cervecitas.earthquakeobserver.presentation.ui.common.view;

/**
 * A {@link MVPView} that loads content.
 */
public interface LoadContentView extends MVPView {

    void showContent();

    void hideContent();

    void showLoading();

    void hideLoading();

    void showRetry();

    void hideRetry();

    void showError(String message);
}
