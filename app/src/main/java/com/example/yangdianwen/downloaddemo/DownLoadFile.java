package com.example.yangdianwen.downloaddemo;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

  //http://gdown.baidu.com/data/wisegame/91319a5a1dfae322/baidu_16785426.apk
/**
 * Created by yangdianwen on 16-7-26.
 */
public class DownLoadFile {

    private static InputStream inputStream;
    private static BufferedReader bufferedReader;
    private static ByteArrayOutputStream mBaos;

    //下载文件的方法
    public static byte[] download(String httpUrl) {
        URL url = null;
        try {
            url = new URL(httpUrl);
            //打开网络链接
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //GET方式请求
            httpURLConnection.setRequestMethod("GET");
            //获取读取流
            inputStream = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //字节缓冲区
            byte[] cache = new byte[1024];
            int length;
            //字节数组输出流
            mBaos = new ByteArrayOutputStream();
            while ((length = inputStream.read(cache)) != -1) {
                mBaos.write(cache, 0, length);
            }
            return mBaos.toByteArray();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //写入SD卡
    public static void writeToSdcard(byte[] byteFile){
        String path = "file";
        String fileName = "1.apk";
        String SDCard = Environment.getExternalStorageDirectory() + "";
        String pathName = SDCard + "/" + path + "/" + fileName;
        //创建文件
        File file = new File(pathName);
        OutputStream os = null;
        InputStream is=null;
        try {
            is = new FileInputStream(new File(""+byteFile) );
            os = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            while (is.read(buffer) != -1) {
                os.write(buffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}