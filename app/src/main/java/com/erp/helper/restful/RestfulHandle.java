package com.erp.helper.restful;

import com.loopj.android.http.RequestHandle;

public class RestfulHandle {

    private RequestHandle mRequestHandle;

    public RestfulHandle(RequestHandle requestHandle) {
        mRequestHandle = requestHandle;
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        return mRequestHandle.cancel(mayInterruptIfRunning);
    }

    public boolean isFinished() {
        return mRequestHandle.isFinished();
    }

    public boolean isCancelled() {
        return mRequestHandle.isCancelled();
    }
}
