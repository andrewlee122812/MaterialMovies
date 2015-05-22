package com.yiyo.materialmovies.model.entities;

import java.util.List;

/**
 * Created by sumset on 22/05/15.
 */
public class PopularShowsApiResponse extends ApiResponse {

    private Number page;
    private List<TvShow> results;
    private Number totalPages;
    private Number totalResults;

    public Number getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Number totalResults) {
        this.totalResults = totalResults;
    }

    public Number getTotalPages() {

        return totalPages;
    }

    public void setTotalPages(Number totalPages) {
        this.totalPages = totalPages;
    }

    public List<TvShow> getResults() {

        return results;
    }

    public void setResults(List<TvShow> results) {
        this.results = results;
    }

    public Number getPage() {

        return page;
    }

    public void setPage(Number page) {
        this.page = page;
    }
}
