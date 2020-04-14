package com.java.network;


import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL: 统一资源定位器，
 * 1. 协议
 * 2. 域名、主机IP
 * 3. 端口
 * 4. 请求资源
 * https://www.baidu.com/s?ie=UTF-8&wd=baidu
 */
public class TestURL {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("https://www.baidu.com:80/s?ie=UTF-8&wd=baidu");
        // 获取四个值
        System.out.println("协议:" + url.getProtocol());
        System.out.println("主机IP|域名:" + url.getHost());
        System.out.println("端口:" + url.getPort());
        System.out.println("请求资源:" + url.getFile());
        System.out.println("请求资源:" + url.getPath());

        // 参数
        System.out.println("参数:" + url.getQuery());
        // 锚点
        System.out.println("锚点:" + url.getRef());
    }
}
