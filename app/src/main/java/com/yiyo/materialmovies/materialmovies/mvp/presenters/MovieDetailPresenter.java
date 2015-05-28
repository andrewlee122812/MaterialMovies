package com.yiyo.materialmovies.materialmovies.mvp.presenters;

import com.yiyo.materialmovies.model.entities.MovieDetailResponse;
import com.yiyo.materialmovies.model.entities.ProductionCompanies;

import java.util.List;

/**
 * Created by sumset on 25/05/15.
 */
public interface MovieDetailPresenter {

    public void showDescription(String description);

    public void showCover(String url);

    public void onResume();

    public void onCreate();

    public void onStop();

    public void showTagLine(String tagLine);

    public void showName(String title);

    public void showCompanies(List<ProductionCompanies> companies);

    public void setChecked();

    public void setPending();

    public void onDetailInformationReceived (MovieDetailResponse response);

    public void onViewedPressed();

    public void onPendingPressed();

    public void showHomepage (String homepage);
}
