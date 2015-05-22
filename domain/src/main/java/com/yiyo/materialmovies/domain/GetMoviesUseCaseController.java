package com.yiyo.materialmovies.domain;

import com.squareup.otto.Subscribe;
import com.yiyo.materialmovies.common.utils.BusProvider;
import com.yiyo.materialmovies.model.MediaDataSource;
import com.yiyo.materialmovies.model.entities.PopularMoviesApiResponse;
import com.yiyo.materialmovies.model.entities.PopularShowsApiResponse;
import com.yiyo.materialmovies.model.rest.RestMovieSource;

/**
 * Created by sumset on 22/05/15.
 */
public class GetMoviesUseCaseController implements GetMoviesUsecase {

    private final MediaDataSource dataSource;
    private final int mode;

    private PopularMoviesApiResponse response;

    public GetMoviesUseCaseController(int mode) {
        this.dataSource = RestMovieSource.getInstance();
        this.mode = mode;

        BusProvider.getRestBusInstance().register(this);
    }

    @Override
    public void getPopularShows() {
        dataSource.getShows();
    }

    @Override
    public void getPopularMovies() {
        dataSource.getMovies();
    }

    @Subscribe
    @Override
    public void onPopularShowsReceived(PopularShowsApiResponse response) {

    }

    @Subscribe
    @Override
    public void onPopularMoviesReceived(PopularMoviesApiResponse response) {
        this.response = response;
        sendShowsToPresenter();
    }

    @Override
    public void sendShowsToPresenter() {
        switch (mode) {
            case GetMoviesUsecase.TV_MOVIES:
                BusProvider.getUIBusInstance().post(response);
                break;
            case GetMoviesUsecase.TV_SHOWS:
                break;
        }

        BusProvider.getRestBusInstance().unregister(this);
    }

    @Override
    public void execute() {
        switch (mode) {
            case GetMoviesUsecase.TV_MOVIES:
                getPopularMovies();
                break;

            case GetMoviesUsecase.TV_SHOWS:
                getPopularShows();
                break;
        }
    }
}
