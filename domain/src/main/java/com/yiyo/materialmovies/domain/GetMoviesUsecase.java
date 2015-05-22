package com.yiyo.materialmovies.domain;

import com.yiyo.materialmovies.model.entities.PopularMoviesApiResponse;
import com.yiyo.materialmovies.model.entities.PopularShowsApiResponse;

/**
 * Created by sumset on 22/05/15.
 */
public interface GetMoviesUsecase extends Usecase {

    public static final int TV_SHOWS = 0;
    public static final int TV_MOVIES = 1;

    public void getPopularShows();

    public void getPopularMovies();

    public void onPopularShowsReceived(PopularShowsApiResponse response);

    public void onPopularMoviesReceived(PopularMoviesApiResponse response);

    public void sendShowsToPresenter();
}
