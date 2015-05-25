package com.yiyo.materialmovies.materialmovies.views.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.yiyo.materialmovies.materialmovies.R;
import com.yiyo.materialmovies.materialmovies.mvp.presenters.PopularShowsPresenter;
import com.yiyo.materialmovies.materialmovies.mvp.presenters.PopularShowsPresenterImpl;
import com.yiyo.materialmovies.materialmovies.mvp.views.PopularMoviesView;
import com.yiyo.materialmovies.materialmovies.views.adapters.MoviesAdapter;
import com.yiyo.materialmovies.model.entities.TvMovie;

import java.util.List;

import butterknife.InjectView;


public class MoviesActivity extends AppCompatActivity implements PopularMoviesView, View.OnClickListener {

    private static final int COLUMNS = 2;

    @InjectView(R.id.activity_main_toolbar) Toolbar toolbar;

    private PopularShowsPresenter popularShowsPresenter;
    private RecyclerView popularMoviesRecycler;
    private ProgressBar loadingProgressBar;
    private MoviesAdapter moviesAdapter;
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fijar el Toolbar como el SupportActionBar
        setActionBar(toolbar);
        getActionBar().setTitle("");
        getActionBar().setHomeAsUpIndicator(getDrawable(R.drawable.ic_menu_white_24dp));

        toolbar.setNavigationOnClickListener(this);

        popularMoviesRecycler.setLayoutManager(new GridLayoutManager(this, COLUMNS));
        popularMoviesRecycler.addItemDecoration(new RecyclerInsetsDecoration(this));
        popularMoviesRecycler.setOnScrollListener(recyclerScrollListener);

        popularShowsPresenter = new PopularShowsPresenterImpl(this);
        popularShowsPresenter.onCreate();
    }

    @Override
    protected void onStop() {
        super.onStop();
        popularShowsPresenter.onStop();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMovies(List<TvMovie> movieList) {
        moviesAdapter = new MoviesAdapter(movieList);
        popularMoviesRecycler.setAdapter(moviesAdapter);
    }

    @Override
    public void showLoading() {
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        errorTextView.setVisibility(View.VISIBLE);
        errorTextView.setText(error);
    }

    @Override
    public void hideError() {
        errorTextView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {

    }

    private RecyclerView.OnScrollListener recyclerScrollListener = new RecyclerView.OnScrollListener() {
        public int lasdDy;
        public boolean flag;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (toolbar == null) {
                throw new IllegalStateException("BooksFragment has not a reference of the main toolbar");
            }

            // Is Scrolling up
            if (dy > 10) {
                if (!flag) {
                    showToolbar();
                    flag = true;
                }
            } // Is Scrolling down
            else if (dy < -10) {
                if (flag) {
                    hideToolbar();
                    flag = false;
                }
            }

            lasdDy = dy;
        }
    };

    private void showToolbar() {
        toolbar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate_up_off));
    }

    private void hideToolbar() {
        toolbar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate_up_on));
    }
}
