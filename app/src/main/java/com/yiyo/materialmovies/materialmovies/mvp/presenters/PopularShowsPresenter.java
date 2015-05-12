package com.yiyo.materialmovies.materialmovies.mvp.presenters;

import com.yiyo.materialmovies.model.entities.PopularMoviesApiResponse;

/**
 * Created by yiyo on 10/05/15.
 */
public interface PopularShowsPresenter {

    public void onCreate();

    public void onStop();

    public void onPopularMoviesReceived(PopularMoviesApiResponse popularMovies);
}
