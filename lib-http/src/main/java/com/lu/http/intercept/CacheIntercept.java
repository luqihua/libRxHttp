package com.lu.http.intercept;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author: luqihua
 * Time: 2017/6/9
 * Description: CacheIntercept
 */

public class CacheIntercept implements Interceptor {

    private String mCCMessage = "max-age=600";

    public CacheIntercept(String cacheControl) {
        if (!TextUtils.isEmpty(cacheControl)) {
            this.mCCMessage = cacheControl;
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request request = chain.request();
        Response response = chain.proceed(request);

        if (!request.method().equals("GET")) {
            return response;
        }

        return response.newBuilder()
                .removeHeader("Pragma")
                .addHeader("Cache-Control", mCCMessage)
                .build();
    }
}
