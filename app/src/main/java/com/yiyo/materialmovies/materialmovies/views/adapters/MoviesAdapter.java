package com.yiyo.materialmovies.materialmovies.views.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.yiyo.materialmovies.common.utils.Constants;
import com.yiyo.materialmovies.materialmovies.R;
import com.yiyo.materialmovies.materialmovies.utils.RecyclerViewClickListener;
import com.yiyo.materialmovies.model.entities.TvMovie;

import java.util.List;

/**
 * Created by sumset on 22/05/15.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private final List<TvMovie> movieList;
    public RecyclerViewClickListener recyclerViewClickListener;
    private Context context;

    public MoviesAdapter(List<TvMovie> movieList) {
        this.movieList = movieList;
    }

    public List<TvMovie> getMovieList() {

        return movieList;
    }

    public void setRecyclerViewClickListener(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View rowView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_movie, viewGroup, false);

        this.context = viewGroup.getContext();

        return new MovieViewHolder(rowView, recyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {

        TvMovie selectedMovie = movieList.get(position);

        holder.titleTextView.setText(selectedMovie.getTitle());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.coverImageView.setTransitionName("cover" + position);
        }

        String posterUrl = Constants.BASIC_STATIC_URL + selectedMovie.getPosterPath();

        Picasso.with(context)
                .load(posterUrl)
                .into(holder.coverImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        movieList.get(position).setMovieReady(true);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    public boolean isMovieReady(int position) {
        return movieList.get(position).isMovieReady();
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}

class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final RecyclerViewClickListener onClickListener;
    TextView titleTextView;
    TextView authorTextView;
    ImageView coverImageView;

    public MovieViewHolder(View itemView, RecyclerViewClickListener onClickListener) {
        super(itemView);

        titleTextView = (TextView) itemView.findViewById(R.id.item_movie_title);
        coverImageView = (ImageView) itemView.findViewById(R.id.item_movie_cover);
        coverImageView.setDrawingCacheEnabled(true);
        coverImageView.setOnClickListener(this);
        this.onClickListener = onClickListener;
    }

    public void setReady(boolean ready) {
        coverImageView.setTag(ready);
    }

    @Override
    public void onClick(View v) {
        onClickListener.onClick(v, getPosition());
    }
}
