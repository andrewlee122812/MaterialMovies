package com.yiyo.materialmovies.materialmovies.views.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.graphics.Palette;
import android.transition.Slide;
import android.transition.Transition;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.yiyo.materialmovies.materialmovies.R;
import com.yiyo.materialmovies.materialmovies.mvp.presenters.MovieDetailPresenter;
import com.yiyo.materialmovies.materialmovies.mvp.presenters.MovieDetailPresenterImpl;
import com.yiyo.materialmovies.materialmovies.mvp.views.MVPDetailView;
import com.yiyo.materialmovies.materialmovies.utils.GUIUtils;
import com.yiyo.materialmovies.materialmovies.utils.MyOwnTransitionListener;
import com.yiyo.materialmovies.materialmovies.views.custom_views.ObservableScrollView;
import com.yiyo.materialmovies.materialmovies.views.custom_views.ScrollViewListener;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;

/**
 * Created by sumset on 25/05/15.
 */
public class MovieDetailActivity extends Activity implements MVPDetailView,
        Palette.PaletteAsyncListener, ScrollViewListener {

    @InjectViews({
        R.id.activity_movie_detail_title,
        R.id.activity_movie_detail_content,
        R.id.activity_detail_homepage_value,
        R.id.activity_detail_company_value,
        R.id.activity_detail_tagline_value,
        R.id.activity_movie_detail_confirmation_text,
    }) List<TextView> movieInfoTextViews;

    @InjectViews({
        R.id.activity_detail_header_tagline,
        R.id.activity_detail_movie_header_description,
    }) List<TextView> headers;

    @InjectView(R.id.activity_detail_book_info) View overviewContainer;
    @InjectView(R.id.activity_movie_detail_fab) ImageView fabButton;
    @InjectView(R.id.activity_movie_detail_cover_wtf) ImageView coverImageView;
    @InjectView(R.id.activity_movide_detail_confirmation_image) ImageView confirmationView;
    @InjectView(R.id.activity_movie_detai_confirmation_container) FrameLayout confirmationContainer;

    @InjectView(R.id.activity_movie_detail_scroll) ObservableScrollView observableScrollView;

    private final int TITLE         = 0;
    private final int DESCRIPTION   = 1;
    private final int HOMEPAGE      = 2;
    private final int COMPANY       = 3;
    private final int TAGLINE       = 4;
    private final int CONFIRMATION  = 5;

    private MovieDetailPresenter detailPresenter;
    private Palette.Swatch mBrightSwatch;
    private Drawable fabRipple;

    private int coverImageHeight;

    @Override
    protected void onResume() {
        super.onResume();
        detailPresenter.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getWindow().getSharedElementEnterTransition().addListener(transitionListener);
        GUIUtils.makeTheStatusbarTranslucent(this);
        ButterKnife.inject(this);

        int moviePosition = getIntent().getIntExtra("movie_position", 0);
        String movieID = getIntent().getStringExtra("movie_id");
        coverImageView.setTransitionName("cover" + moviePosition);

        fabRipple = getResources().getDrawable(R.drawable.ripple_round);
        fabButton.setBackground(fabRipple);

        observableScrollView.setScrollViewListener(this);

        detailPresenter = new MovieDetailPresenterImpl(this, movieID);
        detailPresenter.onCreate();
    }

    @Override
    protected void onStop() {
        super.onStop();
        detailPresenter.onStop();
    }

    @Override
    public void onGenerated(Palette palette) {

        if (palette != null) {
            Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();

            Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();

            Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();

            if (lightVibrantSwatch != null) {

            }
        }
    }

    @Override
    public void setImage(String url) {
        Bitmap bookCoverBitmap = MoviesActivity.photoCache.get(0);
        coverImageView.setBackground(new BitmapDrawable(getResources(), bookCoverBitmap));

        Palette.generateAsync(bookCoverBitmap, this);
    }

    @Override
    public void setName(String title) {
        movieInfoTextViews.get(TITLE).setText(title);
    }

    @Override
    public void setDescription(String description) {
        movieInfoTextViews.get(DESCRIPTION).setText(description);
    }

    @Override
    public void setHomepage(String homepage) {
        movieInfoTextViews.get(HOMEPAGE).setVisibility(View.VISIBLE);
        movieInfoTextViews.get(HOMEPAGE).setText(homepage);
    }

    @Override
    public void setCompanies(String companies) {
        movieInfoTextViews.get(COMPANY).setVisibility(View.VISIBLE);
        movieInfoTextViews.get(COMPANY).setText(companies);
    }

    @Override
    public void setTagline(String tagline) {
        movieInfoTextViews.get(TAGLINE).setText(tagline);
    }

    @Override
    public void finish(String cause) {
        Toast.makeText(this, cause, Toast.LENGTH_SHORT).show();
        this.finish();
    }

    @Override
    public void showConfirmationView() {
        GUIUtils.showViewByRevealEffect(confirmationContainer, fabButton,
                GUIUtils.getWindowWidth(this));
        animateConfirmationView();
        startClosingConfirmationView();
    }

    @Override
    public void animateConfirmationView() {
        Drawable drawable = confirmationView.getDrawable();

        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    @Override
    public void startClosingConfirmationView() {

        int milliseconds = 1500;
        getWindow().setReturnTransition(new Slide());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                observableScrollView.setVisibility(View.GONE);
                MovieDetailActivity.this.finishAfterTransition();
            }
        }, milliseconds);
    }

    @Override
    public Context getContext() {
        return null;
    }

    @OnClick(R.id.activity_movie_detail_fab)
    public void onClick() {
        showConfirmationView();
    }

    @Override
    public void onScrollChanged(ScrollView scrollView, int x, int y, int oldx, int oldy) {

    }

    private final MyOwnTransitionListener transitionListener = new MyOwnTransitionListener() {
        @Override
        public void onTransitionEnd(Transition transition) {
            super.onTransitionEnd(transition);
            GUIUtils.showViewByScale(fabButton);
        }
    };
}
