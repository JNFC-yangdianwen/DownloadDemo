package com.example.yangdianwen.downloaddemo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * Created by yangdianwen on 16-7-27.
 * 创建全局的请求队列
 */
public class HttpApplication extends Application {
    private static RequestQueue requestQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        //volley创建请求队列
       requestQueue = Volley.newRequestQueue(getApplicationContext());
    }
    public static RequestQueue getInstance() {
        return requestQueue;
    }
}
