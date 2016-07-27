package com.example.yangdianwen.downloaddemo;

import android.content.Context;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by yangdianwen on 16-7-27.
 */
public class DownLoad {
    public static void downLoad(String path,Context context)throws Exception
    {
        URL url = new URL(path);
        InputStream is = url.openStream();
        //截取最后的文件名
        String end = path.substring(path.lastIndexOf("."));
        //打开手机对应的输出流,输出到文件中
        OutputStream os = context.openFileOutput("Cache_"+System.currentTimeMillis()+end, Context.MODE_PRIVATE);
        byte[] buffer = new byte[1024];
        int len = 0;
        //从输入流中读取数据,读到缓冲区中
        while((len = is.read(buffer)) > 0)
        {
            os.write(buffer,0,len);
        }
        //关闭输入输出流
        is.close();
        os.close();
    }
}
