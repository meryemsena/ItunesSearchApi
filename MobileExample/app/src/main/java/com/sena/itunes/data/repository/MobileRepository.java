package com.sena.itunes.data.repository;

import com.sena.itunes.data.ItunesApiService;
import com.sena.itunes.data.remote.SearchResult;
import com.sena.itunes.data.utils.NetworkBoundResource;
import com.sena.itunes.data.utils.Resource;
import com.sena.itunes.data.utils.Status;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by Sena KILIÇ on 1/10/2022.
 */

@Singleton
public class MobileRepository {
    private ItunesApiService apiService;

    @Inject
    public MobileRepository(ItunesApiService apiService) {
        this.apiService = apiService;
    }

    public Single<Resource<SearchResult>> search(String query, int offset, int limit) {
        return new NetworkBoundResource<SearchResult, SearchResult>() {
            @Override
            protected void saveCallResult(SearchResult item) {
            }

            @Override
            protected Single<Resource<SearchResult>> createCall() {
                return apiService.searchContent(query, offset, limit)
                        .flatMap(apiResponse -> {
                            if (apiResponse != null) {
                                if (apiResponse.results != null) {
                                    return Single.just(Resource.success(apiResponse));
                                } else {
                                    return Single.just(Resource.error("Başarısız.", apiResponse));
                                }
                            } else {
                                return null;
                            }
                        });
            }

            @Override
            protected Flowable<SearchResult> processError() {
                return Flowable.just(new SearchResult());
            }

            @Override
            protected Flowable<SearchResult> loadFromDb() {
                return null;
            }

            @Override
            protected Flowable<Resource<SearchResult>> processMapping(Resource<SearchResult> response) {
                if (response != null) {
                    if (response.status == Status.SUCCESS) {
                        return Flowable.just(Resource.success(response.data));
                    } else if (response.status == Status.ERROR) {
                        return Flowable.just(Resource.error(response.message, response.data));
                    } else {
                        return Flowable.just(Resource.loading(response.data));
                    }
                } else {
                    return Flowable.empty();
                }
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @Override
            protected void onFetchFailed() {
            }
        }.getAsObservable();
    }
}
