package es.cervecitas.earthquakeobserver.presentation.ui.common.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import es.cervecitas.earthquakeobserver.R;
import es.cervecitas.earthquakeobserver.presentation.ui.common.presenter.Presenter;

/**
 * An abstract {@link BaseViewFragment} that implements {@link LoadContentView}.
 * <p>
 * This fragment assumes that the following Android views with the given ids exist in the view
 * returned in onCreateView:
 * <ul>
 * <li>R.id.content_view : the view that contains the content</li>
 * <li>R.id.loading_indicator : the loading indicator view</li>
 * <li>R.id.retry_button : the retry button view</li>
 * </ul>
 * The above required views must be declared invisible in xml.
 * <p>
 * This uses a {@link Toast} for showing errors via {@link #showError(String)}.
 *
 * @param <T> the type of the {@link Presenter}.
 */
public abstract class AbstractLoadContentFragment<T extends Presenter> extends BaseViewFragment<T> implements LoadContentView {

    @BindView(R.id.swipeContainer)
    protected SwipeRefreshLayout swipeRefreshLayout;

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.contentPane)
    protected View contentPane;

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.retryButton)
    protected View retryButton;

    @Override
    public void showContent() {
        if (contentPane != null && contentPane.getVisibility() != View.VISIBLE) {
            contentPane.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideContent() {
        if (contentPane != null && contentPane.getVisibility() != View.INVISIBLE) {
            contentPane.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showLoading() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideLoading() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showRetry() {
        if (retryButton != null && retryButton.getVisibility() != View.VISIBLE) {
            retryButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideRetry() {
        if (retryButton != null && retryButton.getVisibility() != View.INVISIBLE) {
            retryButton.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(activityContext, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.retryButton)
    protected void onRetryButtonClicked() {
    }
}
