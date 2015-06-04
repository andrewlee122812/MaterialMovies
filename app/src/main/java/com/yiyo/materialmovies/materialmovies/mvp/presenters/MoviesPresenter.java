package com.yiyo.materialmovies.materialmovies.mvp.presenters;

import com.squareup.otto.Subscribe;
import com.yiyo.materialmovies.common.utils.BusProvider;
import com.yiyo.materialmovies.common.utils.Constants;
import com.yiyo.materialmovies.domain.GetMoviesUseCaseController;
import com.yiyo.materialmovies.domain.GetMoviesUsecase;
import com.yiyo.materialmovies.domain.Usecase;
import com.yiyo.materialmovies.materialmovies.mvp.views.MoviesView;
import com.yiyo.materialmovies.model.entities.PopularMoviesApiResponse;
import com.yiyo.materialmovies.model.rest.RestMovieSource;

/**
 * Created by yiyo on 10/05/15.
 */
public class MoviesPresenter extends Presenter {

    private final MoviesView mMoviesView;

    public MoviesPresenter(MoviesView mMoviesView) {
        this.mMoviesView = mMoviesView;
    }

    @Override
    public void onCreate() {
        BusProvider.getUIBusInstance().register(this);

        mMoviesView.showLoading();

        Usecase getPopularShows = new GetMoviesUseCaseController(GetMoviesUsecase.TV_MOVIES);
        getPopularShows.execute();
    }

    @Override
    public void onStop() {
        BusProvider.getUIBusInstance().unregister(this);
    }

    @Subscribe
    public void onPopularMoviesReceived(PopularMoviesApiResponse popularMovies) {
        mMoviesView.hideLoading();
        mMoviesView.showMovies(popularMovies.getResults());
    }

    @Subscribe
    public void onConfigurationFinished(String baseImageUrl) {
        Constants.BASIC_STATIC_URL = baseImageUrl;

        Usecase getPopularShows = new GetMoviesUseCaseController(GetMoviesUsecase.TV_MOVIES,
                RestMovieSource.getInstance(), BusProvider.getUIBusInstance());
        getPopularShows.execute();
    }

    @Override
    public void start() {
        BusProvider.getUIBusInstance().register(this);

        mMoviesView.showLoading();

        Usecase configureUsecase = new ConfigurationUsecaseController(
                RestMovieSource.getInstance(), BusProvider.getUIBusInstance());
        configureUsecase.execute();
    }

    @Override
    public void stop() {
        BusProvider.getUIBusInstance().unregister(this);
    }
}
