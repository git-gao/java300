package com.java.network.chat.encapsulation;

import java.io.*;
import java.net.Socket;

/**
 * 在线聊天室：客户端
 * 使用多线程，实现多个客户可以正常收发消息
 */
public class PackMultiClient {

    public static void main(String[] args) {
        System.out.println("-------------Client------------");
        try {
            System.out.println("请输入用户名：");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String name = br.readLine();
            // 1. 创建时就已经建立连接
            Socket client = new Socket("localhost", 8080);
            // 2. 客户端发送消息
            new Thread(new Send(client, name)).start();
            new Thread(new Receive(client)).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
