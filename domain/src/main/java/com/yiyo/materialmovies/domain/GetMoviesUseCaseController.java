package com.yiyo.materialmovies.domain;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.yiyo.materialmovies.common.utils.BusProvider;
import com.yiyo.materialmovies.model.MediaDataSource;
import com.yiyo.materialmovies.model.entities.PopularMoviesApiResponse;
import com.yiyo.materialmovies.model.entities.PopularShowsApiResponse;
import com.yiyo.materialmovies.model.rest.RestMovieSource;

/**
 * Created by sumset on 22/05/15.
 */
public class GetMoviesUseCaseController implements GetMoviesUsecase, GetMoviesUsecase.MoviesCallback {

    private final MediaDataSource dataSource;
    private final int mode;
    private final Bus uiBus;

    private PopularMoviesApiResponse response;

    public GetMoviesUseCaseController(int mode, MediaDataSource dataSource, Bus uiBus) {

        if (mode == GetMoviesUsecase.TV_SHOWS) {
            throw  new IllegalArgumentException("The Shows feature is not implemented yet");
        }

        if (dataSource == null || uiBus == null) {
            throw new IllegalArgumentException("MediaDataSource & the event bus cannot be null");
        }

        this.dataSource = RestMovieSource.getInstance();
        this.mode = mode;
        this.uiBus = uiBus;

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
                uiBus.post(response);
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
