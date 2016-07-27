package com.example.yangdianwen.downloaddemo;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends Activity implements View.OnClickListener {
//    String path="http://10.13.20.32:8080/Test/db.zip";
    private String url="http://gdown.baidu.com/data/wisegame/91319a5a1dfae322/baidu_16785426.apk";
    private Button downFileBtn;
    private Button downMP3Btn;
    private Context context=this;
    private static final String TAG = "MainActivity";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downFileBtn=(Button)this.findViewById(R.id.volley);
        downMP3Btn=(Button)this.findViewById(R.id.origin);
        downFileBtn.setOnClickListener(this);
        downMP3Btn.setOnClickListener(this);

//        downFileBtn.setOnClickListener(new DownFileClickListener());
//        downMP3Btn.setOnClickListener(new DownMP3ClickListener());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.volley:
                volley_Get();
                break;
            case R.id.origin:
            new MyAsyckTask().execute();
                break;
        }
    }
   //搭建get请求
    private void volley_Get() {
        StringRequest request=new StringRequest(Request.Method.GET, url,
           //响应成功的回调
           new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                byte[] bytes = response.getBytes();
                Log.d(TAG, "onResponse: "+bytes);

            }
        },
             //响应失败的回调
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //设置请求标记（方便后面使用）
        request.setTag("MyRequest");
        //添加请求到全局请求队列中
        HttpApplication.getInstance().add(request);
    }

    public class MyAsyckTask extends AsyncTask<Void,Void,byte[]>{
        //执行任务
        @Override
     protected void onPreExecute() {
            super.onPreExecute();
        }
     //处理数据
    @Override
    protected byte[] doInBackground(Void... params) {
        byte[] download = DownLoadFile.download(url);
        Log.d(TAG, "doInBackground: "+download);
        return download ;
    }
    @Override
    protected void onPostExecute(byte[] file) {
        super.onPostExecute(file);
        Log.d(TAG, "onPostExecute: "+file);
    }
}
    /**
     *
     * @Project: Android_MyDownload
     * @Desciption: 只能读取文本文件，读取mp3文件会出现内存溢出现象
     * @Author: LinYiSong
     * @Date: 2011-3-25~2011-3-25
     */
//    class DownFileClickListener implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//            String urlStr="http://172.17.54.91:8080/download/down.txt";
//            try {
//                /*
//                 * 通过URL取得HttpURLConnection
//                 * 要网络连接成功，需在AndroidMainfest.xml中进行权限配置
//                 * <uses-permission android:name="android.permission.INTERNET" />
//                 */
//                URL url=new URL(urlStr);
//                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
//                //取得inputStream，并进行读取
//                InputStream input=conn.getInputStream();
//                BufferedReader in=new BufferedReader(new InputStreamReader(input));
//                String line=null;
//                StringBuffer sb=new StringBuffer();
//                while((line=in.readLine())!=null){
//                    sb.append(line);
//                }
//                System.out.println(sb.toString());
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    /**
//     *
//     * @Project: Android_MyDownload
//     * @Desciption: 读取任意文件，并将文件保存到手机SDCard
//     * @Author: LinYiSong
//     * @Date: 2011-3-25~2011-3-25
//     */
//    class DownMP3ClickListener implements  View.OnClickListener {
//
//        @Override
//        public void onClick(View v) {
//            String urlStr="http://172.17.54.91:8080/download/1.mp3";
//            String path="file";
//            String fileName="2.mp3";
//            OutputStream output=null;
//            try {
//                /*
//                 * 通过URL取得HttpURLConnection
//                 * 要网络连接成功，需在AndroidMainfest.xml中进行权限配置
//                 * <uses-permission android:name="android.permission.INTERNET" />
//                 */
//                URL url=new URL(urlStr);
//                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
//                //取得inputStream，并将流中的信息写入SDCard
//
//                /*
//                 * 写前准备
//                 * 1.在AndroidMainfest.xml中进行权限配置
//                 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
//                 * 取得写入SDCard的权限
//                 * 2.取得SDCard的路径： Environment.getExternalStorageDirectory()
//                 * 3.检查要保存的文件上是否已经存在
//                 * 4.不存在，新建文件夹，新建文件
//                 * 5.将input流中的信息写入SDCard
//                 * 6.关闭流
//                 */
//                String SDCard= Environment.getExternalStorageDirectory()+"";
//                String pathName=SDCard+"/"+path+"/"+fileName;//文件存储路径
//
//                File file=new File(pathName);
//                InputStream input=conn.getInputStream();
//                if(file.exists()){
//                    System.out.println("exits");
//                    return;
//                }else{
//                    String dir=SDCard+"/"+path;
//                    new File(dir).mkdir();//新建文件夹
//                    file.createNewFile();//新建文件
//                    output=new FileOutputStream(file);
//                    //读取大文件
//                    byte[] buffer=new byte[4*1024];
//                    while(input.read(buffer)!=-1){
//                        output.write(buffer);
//                    }
//                    output.flush();
//                }
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }finally{
//                try {
//                    output.close();
//                    System.out.println("success");
//                } catch (IOException e) {
//                    System.out.println("fail");
//                    e.printStackTrace();
//                }
//            }
//        }

//    }
}

