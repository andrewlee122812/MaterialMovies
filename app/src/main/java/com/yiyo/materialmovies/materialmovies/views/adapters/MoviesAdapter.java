package com.yiyo.materialmovies.materialmovies.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yiyo.materialmovies.common.utils.Constants;
import com.yiyo.materialmovies.materialmovies.R;
import com.yiyo.materialmovies.materialmovies.utils.MyOwnClickListener;
import com.yiyo.materialmovies.model.entities.TvMovie;

import java.util.List;

/**
 * Created by sumset on 22/05/15.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private final List<TvMovie> movieList;
    public MyOwnClickListener myOwnClickListener;
    private Context context;

    public MoviesAdapter(List<TvMovie> movieList) {
        this.movieList = movieList;
    }

    public List<TvMovie> getMovieList() {

        return movieList;
    }

    public void setMyOwnClickListener(MyOwnClickListener myOwnClickListener) {
        this.myOwnClickListener = myOwnClickListener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View rowView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_movie, viewGroup, false);

        this.context = viewGroup.getContext();

        return new MovieViewHolder(rowView, myOwnClickListener);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        TvMovie selectedMovie = movieList.get(position);

        holder.titleTextView.setText(selectedMovie.getTitle());
        holder.coverImageView.setTransitionName("cover" + position);

        String posterUrl = Constants.POSTER_PREFIX + selectedMovie.getPosterPath();

        Picasso.with(context)
                .load(posterUrl)
                .into(holder.coverImageView);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final MyOwnClickListener onClickListener;
    TextView titleTextView;
    TextView authorTextView;
    ImageView coverImageView;

    public MovieViewHolder(View itemView, MyOwnClickListener onClickListener) {
        super(itemView);

        titleTextView = (TextView) itemView.findViewById(R.id.item_movie_title);
        coverImageView = (ImageView) itemView.findViewById(R.id.item_movie_cover);
        coverImageView.setDrawingCacheEnabled(true);
        coverImageView.setOnClickListener(this);
        this.onClickListener = onClickListener;
    }

    @Override
    public void onClick(View v) {
        onClickListener.onClick(v, getPosition());
    }
}
