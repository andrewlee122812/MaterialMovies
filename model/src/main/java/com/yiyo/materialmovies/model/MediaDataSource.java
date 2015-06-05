package com.yiyo.materialmovies.model;

public interface MediaDataSource {

    void getShows();

    void getMovies();

    void getDetailMovie(String id);

    void getConfiguration();
}
