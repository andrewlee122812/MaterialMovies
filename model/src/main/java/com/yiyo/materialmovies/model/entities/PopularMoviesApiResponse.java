package com.yiyo.materialmovies.model.entities;

import java.util.List;

/**
 * Created by yiyo on 10/05/15.
 */
public class PopularMoviesApiResponse extends ApiResponse {

    private Number page;
    private List<TvMovie> results;
    private Number totalPages;
    private Number totalResults;

    public Number getTotalResults() {
        return totalResults;
    }

    public void setTotal_results(Number totalResults) {
        this.totalResults = totalResults;
    }

    public Number getPage() {
        return page;
    }

    public void setPage(Number page) {
        this.page = page;
    }

    public List<TvMovie> getResults() {
        return results;
    }

    public void setResults(List<TvMovie> results) {
        this.results = results;
    }

    public Number getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Number totalPages) {
        this.totalPages = totalPages;
    }
}
