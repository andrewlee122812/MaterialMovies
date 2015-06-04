package com.yiyo.materialmovies.materialmovies.views.activities;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import com.yiyo.materialmovies.materialmovies.R;
import com.yiyo.materialmovies.materialmovies.mvp.presenters.MoviesPresenter;
import com.yiyo.materialmovies.materialmovies.mvp.views.MoviesView;
import com.yiyo.materialmovies.materialmovies.utils.MyOwnClickListener;
import com.yiyo.materialmovies.materialmovies.utils.RecyclerInsetsDecoration;
import com.yiyo.materialmovies.materialmovies.views.adapters.MoviesAdapter;
import com.yiyo.materialmovies.materialmovies.views.fragments.NavigationDrawerFragment;
import com.yiyo.materialmovies.model.entities.TvMovie;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MoviesActivity extends AppCompatActivity implements MoviesView,
        MyOwnClickListener, View.OnClickListener {

    /**
     * Number of columns in the RecyclerView
     */
    private static final int COLUMNS = 2;

    /**
     * A container used between this activity and MovieDetailActivity
     * to share a Bitmap with a SharedElementTransition
     */
    public static SparseArray<Bitmap> photoCache = new SparseArray<Bitmap>(1);

    private MoviesAdapter mMoviesAdapter;
    private MoviesPresenter mMoviesPresenter;

    private NavigationDrawerFragment mNavigationDrawerFragment;

    @InjectView(R.id.recycler_popular_movies) RecyclerView mRecycler;
    @InjectView(R.id.activity_main_progress) ProgressBar mProgressBar;
    @InjectView(R.id.activity_main_toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        // Fijar el Toolbar como el SupportActionBar
        setActionBar(mToolbar);
        getActionBar().setTitle("");

        getActionBar().setHomeAsUpIndicator(getDrawable(R.drawable.ic_menu_white_24dp));
        mToolbar.setNavigationOnClickListener(this);

        mRecycler.setLayoutManager(new GridLayoutManager(this, COLUMNS));
        mRecycler.addItemDecoration(new RecyclerInsetsDecoration(this));
        mRecycler.setOnScrollListener(recyclerScrollListener);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Setup the drawer
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        mMoviesPresenter = new MoviesPresenter(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMoviesPresenter.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMoviesPresenter.start();
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
        mMoviesAdapter = new MoviesAdapter(movieList);
        mMoviesAdapter.setMyOwnClickListener(this);
        mRecycler.setAdapter(mMoviesAdapter);
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void hideError() {

    }

    @Override
    public void onClick(View v, int position) {
        Intent i = new Intent (MoviesActivity.this, MovieDetailActivity.class);

        String movieID = mMoviesAdapter.getMovieList().get(position).getId();

        i.putExtra("movie_id", movieID);
        i.putExtra("movie_position", position);

        ImageView coverImage = (ImageView) v.findViewById(R.id.item_movie_cover);
        photoCache.put(0, coverImage.getDrawingCache());

        if () {

        }

        // Setup the transition to the detail activity
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                        new Pair<View, String>(v, "cover" + position));

        startActivity(i, options.toBundle());
    }

    private RecyclerView.OnScrollListener recyclerScrollListener = new RecyclerView.OnScrollListener() {
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

    @Override
    public void onClick(View v) {

    }
}
