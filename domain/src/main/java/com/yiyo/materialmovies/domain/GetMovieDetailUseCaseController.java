package com.yiyo.materialmovies.domain;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.yiyo.materialmovies.common.utils.BusProvider;
import com.yiyo.materialmovies.model.MediaDataSource;
import com.yiyo.materialmovies.model.entities.MovieDetailResponse;
import com.yiyo.materialmovies.model.rest.RestMovieSource;

/**
 * Created by yiyo on 2/06/15.
 */
public class GetMovieDetailUseCaseController implements GetMovieDetail {

    private final String movieID;
    private final MediaDataSource movieDataSource;

    public GetMovieDetailUseCaseController(String movieID) {
        this.movieID = movieID;
        movieDataSource = RestMovieSource.getInstance();
        BusProvider.getRestBusInstance().register(this);
    }

    @Override
    public void requestMovieDetail(String id) {
        movieDataSource.getDetailMovie(id);
    }

    @Subscribe
    @Override
    public void sendResponseToPresenter(MovieDetailResponse response) {
        BusProvider.getUIBusInstance().post(response);
        BusProvider.getRestBusInstance().unregister(this);
    }

    @Override
    public void execute() {
        requestMovieDetail(movieID);
    }
}
