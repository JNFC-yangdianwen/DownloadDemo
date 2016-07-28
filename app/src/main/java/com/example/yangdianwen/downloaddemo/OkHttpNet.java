package com.example.yangdianwen.downloaddemo;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by yangdianwen on 16-7-28.
 * 一个封装OkHttpClient的类
 */
public class OkHttpNet {
    private static OkHttpNet okHttpNet;
    private static OkHttpClient okHttpClient;
    Request request;
    public static OkHttpNet getInstance() {
        if (okHttpNet == null) {
            okHttpNet=new OkHttpNet();
        }
        return okHttpNet ;
    }

    public OkHttpNet() {

    }
    //创建call模型
    public Call getCall(){
        okHttpClient= new OkHttpClient.Builder().build();
        request=new Request.Builder().url(MainActivity.url).build();
        Call call = okHttpClient.newCall(request);
        return call;
    }
}
