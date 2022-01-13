package com.sena.itunes.data.remote;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Sena KILIÇ on 1/10/2022.
 */

public class UrlInterceptor implements Interceptor {
    private volatile String url;

    @Inject
    public UrlInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String url = this.url;
        if (url != null) {
            HttpUrl newUrl = HttpUrl.parse(url);
            if (newUrl != null) {
                request = request.newBuilder()
                        .url(newUrl)
                        .build();
            }
        }
        return chain.proceed(request);
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
