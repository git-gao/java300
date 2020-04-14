package com.java.network.chat;

import java.io.*;
import java.net.Socket;

/**
 * 在线聊天室：客户端
 * 实现一个客户可以正常收发消息
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("-------------Client------------");
        try {
            // 1. 建立连接
            Socket client = new Socket("localhost", 8080);
            // 2. 客户端发送消息
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String msg = br.readLine();
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF(msg);
            dos.flush();
            // 3. 获取消息
            DataInputStream dis = new DataInputStream(client.getInputStream());
            msg = dis.readUTF();
            System.out.println(msg);
            // 4. 释放资源
            dis.close();
            dos.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
