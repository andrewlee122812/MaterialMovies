package com.yiyo.materialmovies.materialmovies.mvp.presenters;

import com.squareup.otto.Subscribe;
import com.yiyo.materialmovies.common.utils.BusProvider;
import com.yiyo.materialmovies.domain.GetMoviesUseCaseController;
import com.yiyo.materialmovies.domain.GetMoviesUsecase;
import com.yiyo.materialmovies.domain.Usecase;
import com.yiyo.materialmovies.materialmovies.mvp.views.PopularMoviesView;
import com.yiyo.materialmovies.model.entities.PopularMoviesApiResponse;

/**
 * Created by yiyo on 10/05/15.
 */
public class PopularShowsPresenterImpl implements PopularShowsPresenter {

    private final PopularMoviesView popularMoviesView;

    public PopularShowsPresenterImpl(PopularMoviesView popularMoviesView) {
        this.popularMoviesView = popularMoviesView;
    }

    @Override
    public void onCreate() {
        BusProvider.getUIBusInstance().register(this);

        popularMoviesView.showLoading();

        Usecase getPopularShows = new GetMoviesUseCaseController(GetMoviesUsecase.TV_MOVIES);
        getPopularShows.execute();
    }

    @Override
    public void onStop() {
        BusProvider.getUIBusInstance().unregister(this);
    }

    @Subscribe
    @Override
    public void onPopularMoviesReceived(PopularMoviesApiResponse popularMovies) {
        popularMoviesView.hideLoading();
        popularMoviesView.showMovies(popularMovies.getResults());
    }
}
