package com.erp.helper.restful;

import android.content.Context;
import android.net.Uri;

import com.erp.wms.R;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.SyncHttpClient;

import org.apache.http.auth.AuthScope;

public class RestfulRequestSynch extends SyncHttpClient {

    private Context mContext;
    private String mApiUrl;

    public RestfulRequestSynch(Context context) {
        super();

        mContext = context;
        mApiUrl = mContext.getString(R.string.api_url);

        PersistentCookieStore persistentCookieStore = new PersistentCookieStore(mContext);
        this.setCookieStore(persistentCookieStore);

        this.setMaxRetriesAndTimeout(1, 60000);

        //this.clearCredentialsProvider();
        Uri parsed = Uri.parse(mApiUrl);
        //this.setCredentials(
        //        new AuthScope(parsed.getHost(), parsed.getPort() == -1 ? 80 : parsed.getPort()),
        //        new UsernamePasswordCredentials("admin", "p5ssWrd4Dm1N")
        //);
        this.setBasicAuth("admin", "p5ssWrd4Dm1N",
                new AuthScope(parsed.getHost(), parsed.getPort() == -1 ? 80 : parsed.getPort(), AuthScope.ANY_REALM)
        );

        this.addHeader("X-Requested-With", "XMLHttpRequest");
    }

    public RestfulHandle get(String api, RestfulParam param, RestfulResponse response) {
        RequestHandle requestHandle = super.get(mApiUrl + api, param, response);
        return new RestfulHandle(requestHandle);
    }

    public RestfulHandle post(String api, RestfulParam param, RestfulResponse response) {
        RequestHandle requestHandle = super.post(mApiUrl + api, param, response);
        return new RestfulHandle(requestHandle);
    }
}
