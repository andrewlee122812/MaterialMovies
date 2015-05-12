package com.yiyo.materialmovies.materialmovies.mvp.presenters;

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

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPopularMoviesReceived(PopularMoviesApiResponse popularMovies) {

    }
}
