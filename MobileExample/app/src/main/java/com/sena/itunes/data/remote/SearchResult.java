package com.sena.itunes.data.remote;

import java.util.List;

/**
 * Created by Sena KILIÇ on 1/10/2022.
 */

public class SearchResult {
    public int resultCount;
    public List<SearchResponse> results;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<SearchResponse> getResults() {
        return results;
    }

    public void setResults(List<SearchResponse> results) {
        this.results = results;
    }
}
