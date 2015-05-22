package com.yiyo.materialmovies.model.rest;

import com.yiyo.materialmovies.common.utils.Constants;
import com.yiyo.materialmovies.model.MediaDataSource;

import retrofit.RestAdapter;

/**
 * Created by sumset on 22/05/15.
 */
public class RestMovieSource implements MediaDataSource {

    public static RestMovieSource INSTANCE;
    private final MovieDatabaseAPI moviesDBApi;

    private RestMovieSource() {
        RestAdapter parkAppRest = new RestAdapter.Builder()
                .setEndpoint(Constants.MOVIE_DB_HOST)
                .build();

        moviesDBApi = parkAppRest.create(MovieDatabaseAPI.class);
    }

    public static RestMovieSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RestMovieSource();
        }
        return INSTANCE;
    }

    @Override
    public void getShows() {

    }

    @Override
    public void getMovies() {

    }

    @Override
    public void getDetailMovie() {

    }
}
