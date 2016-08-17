package com.erp.helper.restful;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

public class RestfulResponse extends JsonHttpResponseHandler {

    protected ProgressDialog progressDialog;
    private Context mContext;

    public RestfulResponse(Context context) {
        progressDialog = new ProgressDialog(context);
        mContext = context;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (progressDialog != null) {
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Please wait...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setProgress(0);
            progressDialog.show();
        }
    }

    public void onSuccess(org.json.JSONObject response) {

    }

    public void onSuccess(org.json.JSONArray response) {

    }

    public void onSuccess(int statusCode, org.apache.http.Header[] headers, org.json.JSONObject response) {
        this.onSuccess(response);
    }

    public void onSuccess(int statusCode, org.apache.http.Header[] headers, org.json.JSONArray response) {
        this.onSuccess(response);
    }

    public void onSuccess(int statusCode, org.apache.http.Header[] headers, String responseString) {
        this.showErrorMessage(statusCode, null, null);
    }

    public void onFailure(int statusCode, org.json.JSONObject errorResponse) {

    }

    public void onFailure(int statusCode, org.apache.http.Header[] headers, Throwable throwable, org.json.JSONObject errorResponse) {
        this.showErrorMessage(statusCode, throwable, errorResponse);
        this.onFailure(statusCode, errorResponse);
    }

    public void onFailure(int statusCode, org.apache.http.Header[] headers, Throwable throwable, org.json.JSONArray errorResponse) {
        this.showErrorMessage(statusCode, throwable, null);
        this.onFailure(statusCode, null);
    }

    public void onFailure(int statusCode, org.apache.http.Header[] headers, String responseString, Throwable throwable) {
        this.showErrorMessage(statusCode, throwable, null);
        this.onFailure(statusCode, null);
    }

    public void showErrorMessage(int statusCode, Throwable error, org.json.JSONObject errorResponse) {
        String message = "Error";

        if (statusCode == 400){
            message = "Server understood the request but request content was invalid.";
        }
        else if (statusCode == 401){
            message = "Unauthorised access.";
        }
        else if (statusCode == 403){
            message = "Forbidden resource can't be accessed.";
        }
        else if (statusCode == 404){
            message = "Requested not found.";
        }
        else if (statusCode == 500){
            message = "Internal server error.";
        }
        else if (statusCode == 503){
            message = "Service unavailable.";
        }

        try {
            if (errorResponse != null) {
                if (!errorResponse.isNull("message"))
                    message += (message != null ? " " : "") + errorResponse.getString("message");
            }
        }
        catch (org.json.JSONException ex) {
            message += (message != null ? " " : "") + ex.getMessage();
        }
        catch (Exception ex) {
            message += (message != null ? " " : "") + ex.getMessage();
        }

        if (error != null && message == null) {
            message = error.getMessage();
        }

        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProgress(int bytesWritten, int totalSize) {
        super.onProgress(bytesWritten, totalSize);

        if (progressDialog != null) {
            progressDialog.setMax(totalSize);
            progressDialog.setProgress(bytesWritten);
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();

        if (progressDialog != null) {
            progressDialog.hide();
        }
    }

    public Context getContext() {
        return mContext;
    }
}
