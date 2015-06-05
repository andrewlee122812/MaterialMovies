package com.yiyo.materialmovies.materialmovies.mvp.presenters;

import android.text.TextUtils;

import com.squareup.otto.Subscribe;
import com.yiyo.materialmovies.common.utils.Constants;
import com.yiyo.materialmovies.materialmovies.mvp.views.DetailView;
import com.yiyo.materialmovies.model.entities.MovieDetailResponse;
import com.yiyo.materialmovies.model.entities.ProductionCompanies;

import java.util.List;

/**
 * Created by sumset on 5/06/15.
 */
public class MovieDetailPresenter extends Presenter {

    private final DetailView mMovieDetailView;
    private final String mMovieID;

    public MovieDetailPresenter(DetailView movieDetailView, String movieID) {
        mMovieDetailView = movieDetailView;
        mMovieID = movieID;
    }

    public void showDescription(String description) {

        mMovieDetailView.setDescription(description);
    }

    public void showCover(String url) {

        String coverUrl = Constants.BASIC_STATIC_URL + url;
        mMovieDetailView.setImage(coverUrl);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    public void showTagline(String tagLine) {

        mMovieDetailView.setTagline(tagLine);
    }

    public void showTitle(String title) {

        mMovieDetailView.setName(title);
    }

    public void showCompanies(List<ProductionCompanies> companies) {

        String companiesString = "";

        for (int i = 0; i < companies.size(); i++) {

            ProductionCompanies company = companies.get(i);
            companiesString += company.getName();

            if (i != companies.size() -1)
                companiesString += ", ";
        }

        if (!companies.isEmpty())
            mMovieDetailView.setCompanies(companiesString);
    }

    @Subscribe
    public void onDetailInformationReceived(MovieDetailResponse response) {

        showDescription(response.getOverview());
        showTitle(response.getTitle());
        showCover(response.getPosterPath());
        showTagline(response.getTagline());
        showCompanies(response.getProductionCompanies());
        showHomepage(response.getHomepage());
    }

    public void showHomepage(String homepage) {

        if (!TextUtils.isEmpty(homepage))
            mMovieDetailView.setHomepage(homepage);
    }
}
