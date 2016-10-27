package com.intelink.express.mylibrary.okhttp;

import com.intelink.express.mylibrary.interfaces.Callback;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 陆维淋 on 2016/10/26.
 */

public class HttpGet {

    private void request(final String url,final Callback callback){
        final OkHttpClient mOkHttpClient=new OkHttpClient();
        final Request request=new Request.Builder().url(url).get().build();

        new Thread(new Runnable() {

            @Override
            public void run() {
                Response response;
                try {
                    response = mOkHttpClient.newCall(request).execute();
                    if(response.isSuccessful()){
                        callback.onCallback(response.body().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
