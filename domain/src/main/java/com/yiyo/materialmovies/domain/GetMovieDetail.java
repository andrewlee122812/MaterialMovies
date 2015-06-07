package com.yiyo.materialmovies.domain;

import com.yiyo.materialmovies.model.entities.MovieDetailResponse;

/**
 * Created by yiyo on 2/06/15.
 */
public interface GetMovieDetail extends Usecase {

    void requestMovieDetail(String id);

    void sendResponseToPresenter(MovieDetailResponse response);
}
