package com.yiyo.materialmovies.model.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sumset on 22/05/15.
 */
public class TvShow {
    private Number id;
    private String name;
    private Number popularity;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("origin_country")
    private List originCountry;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("vote_average")
    private Number voteAverage;

    @SerializedName("vote_count")
    private Number voteCount;

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List originCountry) {
        this.originCountry = originCountry;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Number getPopularity() {
        return popularity;
    }

    public void setPopularity(Number popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Number getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Number voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Number getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Number voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        return "TvShow{" +
                "backdrop_path='" + backdropPath + '\'' +
                ", first_air_date='" + firstAirDate + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", origin_country=" + originCountry +
                ", original_name='" + originalName + '\'' +
                ", popularity=" + popularity +
                ", poster_path='" + posterPath + '\'' +
                ", vote_average=" + voteAverage +
                ", vote_count=" + voteCount +
                '}';
    }
}
