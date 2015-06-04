package com.yiyo.materialmovies.materialmovies.mvp.views;

import com.yiyo.materialmovies.model.entities.TvMovie;

import java.util.List;

/**
 * Created by yiyo on 10/05/15.
 */
public interface MoviesView extends MVPView {

    void showMovies(List<TvMovie> movieList);

    void showLoading();

    void hideLoading();

    void showError(String error);

    void hideError();
}
