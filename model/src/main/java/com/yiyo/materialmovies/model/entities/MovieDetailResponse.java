package com.yiyo.materialmovies.model.entities;

import java.util.List;

/**
 * Created by sumset on 22/05/15.
 */
public class MovieDetailResponse {

    private boolean adult;
    private String backdropPath;
    private BelongsToCollection belongsToCollection;
    private Number budget;
    private List genres;
    private String homepage;
    private Number id;
    private String imdbId;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private Number popularity;
    private String posterPath;
    private List productionCompanies;
    private List productionCountries;
    private String releaseDate;
    private Number revenue;
    private Number runtime;
    private List spokenLanguages;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private Number voteAverage;
    private Number voteCount;

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public BelongsToCollection getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(BelongsToCollection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public Number getBudget() {
        return budget;
    }

    public void setBudget(Number budget) {
        this.budget = budget;
    }

    public List getGenres() {
        return genres;
    }

    public void setGenres(List genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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

    public List getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List productionCountries) {
        this.productionCountries = productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Number getRevenue() {
        return revenue;
    }

    public void setRevenue(Number revenue) {
        this.revenue = revenue;
    }

    public Number getRuntime() {
        return runtime;
    }

    public void setRuntime(Number runtime) {
        this.runtime = runtime;
    }

    public List getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
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
}
