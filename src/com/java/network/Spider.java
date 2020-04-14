package com.java.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 模拟爬虫
 */
public class Spider {

    public static void main(String[] args) throws MalformedURLException {
        // 获取 URL
        URL url = new URL("https://www.dianping.com/");
        InputStream is = null;
        try {
            // 下载资源
            //is = url.openStream();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 模拟浏览器
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String msg = null;
            while (null != (msg = br.readLine())) {
                System.out.println(msg);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
