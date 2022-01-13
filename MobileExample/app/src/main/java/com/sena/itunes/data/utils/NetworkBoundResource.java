package com.sena.itunes.data.utils;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public abstract class NetworkBoundResource<ResultType, RequestType> {

    private Single<Resource<ResultType>> result;

    @MainThread
    protected NetworkBoundResource() {
        Single<Resource<ResultType>> source;
        if (shouldFetch()) {
            source = createCall()
                    .subscribeOn(Schedulers.io())
                    .doOnSuccess(apiResponse -> {
                        if (apiResponse.status == Status.SUCCESS) {
                            saveCallResult(processResponse(apiResponse));
                        }
                    })
                    .flatMap(apiResponse -> processMapping(apiResponse).firstOrError())
                    .doOnError(t -> onFetchFailed())
                    .onErrorResumeNext(t -> {

                        Log.d("NetworkBoundResource", Arrays.toString(t.getStackTrace()));
                        Log.d("NetworkBoundResource", t.toString());
                        if (t instanceof ConnectException || t instanceof HttpException) {
                            return processError().firstOrError().map(data -> Resource.error("Bağlantı kurulamadı", data));
                        } else if (t instanceof SocketTimeoutException) {
                            return processError().firstOrError().map(data -> Resource.error("Bağlantı zaman aşımına uğradı", data));
                        } else if (t instanceof UnknownHostException) {
                            return processError().firstOrError().map(data -> Resource.error("Adres tanınamadı", data));
                        } else {
                            return processError().firstOrError().map(data -> Resource.error(t.toString(), data));
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            source = loadFromDb().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).firstOrError().map(Resource::success);
        }

        result = source;
    }

    public Single<Resource<ResultType>> getAsObservable() {
        return result;
    }

    protected void onFetchFailed() {
    }


    @WorkerThread
    protected RequestType processResponse(Resource<RequestType> response) {
        return response.data;
    }


    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    @NonNull
    @MainThread
    protected abstract Single<Resource<RequestType>> createCall();

    @NonNull
    @MainThread
    protected abstract Flowable<ResultType> processError();

    @MainThread
    protected abstract Flowable<ResultType> loadFromDb();

    @WorkerThread
    protected abstract Flowable<Resource<ResultType>> processMapping(Resource<RequestType> response);

    @MainThread
    protected abstract boolean shouldFetch();
}