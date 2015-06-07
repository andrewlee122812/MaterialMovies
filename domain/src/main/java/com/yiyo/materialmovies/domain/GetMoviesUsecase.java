package com.yiyo.materialmovies.domain;

import com.yiyo.materialmovies.model.entities.PopularMoviesApiResponse;
import com.yiyo.materialmovies.model.entities.PopularShowsApiResponse;

/**
 * Created by sumset on 22/05/15.
 */
public interface GetMoviesUsecase extends Usecase {

    public interface MoviesCallback {

        void onPopularMoviesReceived(PopularMoviesApiResponse response);
    }

    public static final int TV_SHOWS = 0;
    public static final int TV_MOVIES = 1;

    void getPopularShows();

    void getPopularMovies();

    void onPopularShowsReceived(PopularShowsApiResponse response);

    void onPopularMoviesReceived(PopularMoviesApiResponse response);

    void sendShowsToPresenter();
}
