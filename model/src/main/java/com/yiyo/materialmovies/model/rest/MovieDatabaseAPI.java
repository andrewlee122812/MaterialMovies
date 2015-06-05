package com.yiyo.materialmovies.model.rest;

import com.yiyo.materialmovies.model.entities.ConfigurationResponse;
import com.yiyo.materialmovies.model.entities.MovieDetailResponse;
import com.yiyo.materialmovies.model.entities.PopularMoviesApiResponse;
import com.yiyo.materialmovies.model.entities.PopularShowsApiResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by sumset on 22/05/15.
 */
public interface MovieDatabaseAPI {

    @GET("/tv/popular")
    void getPopularShows(@Query("api_key") String apiKey, Callback<PopularShowsApiResponse> callback);

    @GET("/movie/popular")
    void getPopularMovies(@Query("api_key") String apiKey, Callback<PopularMoviesApiResponse> callback);

    @GET("/movie/{id}")
    void getMovieDetail(@Query("api_key") String apiKey, @Path("id") String id,
                        Callback<MovieDetailResponse> callback);

    @GET("/configuration")
    void getConfiguration(@Query("api_key") String apiKey, Callback<ConfigurationResponse> response);
}
