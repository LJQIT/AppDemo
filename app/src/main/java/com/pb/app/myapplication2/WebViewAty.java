package com.pb.app.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebViewAty extends AppCompatActivity {
    private WebView wv;
    private static final int TIME_OUT_MS=15000;
    private String authorize="http://www.oschina.net/action/oauth2/authorize?response_type=code&client_id=s7MqyTYZJmekjfY1326Z&redirect_uri=http://linjianqinggit.com";
    private String authorizeToken="http://www.oschina.net/action/openapi/token?client_id=s7MqyTYZJmekjfY1326Z&client_secret=ecvVWQM3pT3D1D4R1Wh5P6I3bngnBDFa&grant_type=authorization_code&redirect_uri=http://linjianqinggit.com&code=";
    private String authorizeCode;
    private String urlNewsList="https://www.oschina.net/action/openapi/news_list?access_token=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_aty);

        wv= (WebView) findViewById(R.id.wv);
        WebSettings settings=wv.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        wv.loadUrl(authorize);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                authorizeCode=splitCode(url);
                requestToken();
                return true;
            }
        });



    }
//    private  String exampleURL="";
    private String splitCode(String codeURL){
        String code=null;
        String[] split=codeURL.split("&");
        for (int i = 0; i <split.length ; i++) {
            String str=split[i];
            if (str.contains("code=")){
                String[] codeSplit=str.split("=");
                code=codeSplit[1];
            }
        }
        return code;
    }

    private void requestToken() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = authorizeToken + authorizeCode;
                try {
                    String result = get(url);
                    JSONObject jObj = new JSONObject(result);
                    String token = jObj.getString("access_token");

                    Log.e("token", token);
                    String newsList = get(urlNewsList + token);

                    Log.e("------------", newsList);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String get(String url)throws IOException {

        StringBuilder builder=new StringBuilder();

        //获取到我们的URL
        URL requestURL=new URL(url);
        //通过URL，开启一个HttpURLConnection
        HttpURLConnection connection= (HttpURLConnection) requestURL.openConnection();
        //设置请求对象一些属性方法
        connection.setRequestMethod("GET");//设置请求方法
        connection.setConnectTimeout(TIME_OUT_MS);//设置连接超时时间
        connection.setReadTimeout(TIME_OUT_MS);//设置读取超时时间
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);//设置禁用缓存
        //通过返回码判断是否请求成功
        if (connection.getResponseCode()==HttpURLConnection.HTTP_OK){
            //获取网络数据流
            InputStream is=connection.getInputStream();
            int len=0;
            byte[] buffer=new byte[1024];
            while ((len=is.read(buffer))!=-1){
                String respone=new String(buffer,0,len);
                builder.append(respone);
            }
        }
        //断开连接
        connection.disconnect();
        return builder.toString();
    }
}
