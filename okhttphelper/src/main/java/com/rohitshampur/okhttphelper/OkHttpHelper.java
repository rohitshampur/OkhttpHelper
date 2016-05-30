package com.rohitshampur.okhttphelper;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by rohit on 30/5/16.
 */
public class OkHttpHelper {
    private static final String TAG = OkHttpHelper.class.getSimpleName();
    private OkHttpClient client;
    private boolean logUrls;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public OkHttpHelper(OkHttpClient client, boolean logUrls) {
        this.client = client;
        this.logUrls = logUrls;
    }

    /**
     * @param url String Url
     * @param params Map<String,String> params
     * @return String response
     * @throws IOException
     */
    public String httpGet(String url, Map<String, String> params) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            if (i == 0) {
                String value = (new ArrayList<String>(params.values())).get(i);
                String key = (new ArrayList<String>(params.keySet())).get(i);
                builder.append(key + "=" + value);
            } else {
                String value = (new ArrayList<String>(params.values())).get(i);
                String key = (new ArrayList<String>(params.keySet())).get(i);
                builder.append("&" + key + "=" + value);
            }

        }
        url += "?" + builder.toString();
        if (logUrls) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, url);
            }
        }
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * @param url String url
     * @return String response
     * @throws IOException
     */

    public String httpGet(String url) throws IOException {
        if (logUrls) {
            Log.d(TAG, "URL = " + url);
        }
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();

    }

    /**
     * @param url  String Url
     * @param json String json
     * @return String response
     * @throws IOException
     */
    public String httpPost(String url, String json) throws IOException {
        if (logUrls) {
            Log.d(TAG, "URL = " + url);
            Log.d(TAG, "Body = " + json);
        }
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * @param url        String Url
     * @param fileName   String filename
     * @param file       File file
     * @param jsonString String jsonData
     * @return String response
     * @throws IOException
     */
    public String httpUpload(String url, String fileName, File file, String jsonString) throws IOException {
        client = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();
        RequestBody formBody = new MultipartBody.Builder().setType(MultipartBody.MIXED).addFormDataPart(fileName, file.getName(), RequestBody.create(MediaType.parse("image/*"), file)).addFormDataPart("data", jsonString).build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * @param fileName   String filename
     * @param file       File file
     * @return String response
     * @throws IOException
     */
    public String httpUpload(String url, String fileName, File file) throws IOException {
        client = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();
        RequestBody formBody = new MultipartBody.Builder().setType(MultipartBody.MIXED).addFormDataPart(fileName, file.getName(), RequestBody.create(MediaType.parse("image/*"), file)).build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
