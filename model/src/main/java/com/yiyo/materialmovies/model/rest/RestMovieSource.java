package com.yiyo.materialmovies.model.rest;

import com.yiyo.materialmovies.common.utils.BusProvider;
import com.yiyo.materialmovies.common.utils.Constants;
import com.yiyo.materialmovies.model.MediaDataSource;
import com.yiyo.materialmovies.model.entities.ConfigurationResponse;
import com.yiyo.materialmovies.model.entities.MovieDetailResponse;
import com.yiyo.materialmovies.model.entities.PopularMoviesApiResponse;

import java.util.logging.Logger;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by sumset on 22/05/15.
 */
public class RestMovieSource implements MediaDataSource {

    public static RestMovieSource INSTANCE;
    private final MovieDatabaseAPI moviesDBApi;

    private RestMovieSource() {
        RestAdapter movieAPIRest = new RestAdapter.Builder()
                .setEndpoint(Constants.MOVIE_DB_HOST)
                .build();

        moviesDBApi = movieAPIRest.create(MovieDatabaseAPI.class);
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
        moviesDBApi.getPopularMovies(Constants.API_KEY, retrofitCallback);
    }

    @Override
    public void getDetailMovie(String id) {
        moviesDBApi.getMovieDetail(Constants.API_KEY, id, retrofitCallback);
    }

    @Override
    public void getConfiguration() {
        moviesDBApi.getConfiguration(Constants.API_KEY, retrofitCallback);
    }

    public Callback retrofitCallback = new Callback() {
        @Override
        public void success(Object o, Response response) {
            if (o instanceof MovieDetailResponse) {

                MovieDetailResponse detailResponse = (MovieDetailResponse) o;
                BusProvider.getRestBusInstance().post(detailResponse);
            } else if (o instanceof PopularMoviesApiResponse) {

                PopularMoviesApiResponse moviesApiResponse = (PopularMoviesApiResponse) o;
                BusProvider.getRestBusInstance().post(moviesApiResponse);
            } else if (o instanceof ConfigurationResponse) {

                ConfigurationResponse configurationResponse = (ConfigurationResponse) o;
                BusProvider.getRestBusInstance().post(configurationResponse);
            }
        }

        @Override
        public void failure(RetrofitError error) {
            System.out.printf("[DEBUG] RestMovieSource failure - " + error.getMessage());
        }
    };
}
