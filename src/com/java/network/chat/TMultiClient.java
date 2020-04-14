package com.java.network.chat;

import java.io.*;
import java.net.Socket;

/**
 * 在线聊天室：客户端
 * 使用多线程，实现多个客户可以正常收发消息
 */
public class TMultiClient {

    public static void main(String[] args) {
        System.out.println("-------------Client------------");
        try {
            // 1. 建立连接
            Socket client = new Socket("localhost", 8080);
            // 2. 客户端发送消息
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            DataInputStream dis = new DataInputStream(client.getInputStream());
            boolean isRunning = true;
            while (isRunning) {
                String msg = br.readLine();
                dos.writeUTF(msg);
                dos.flush();
                // 3. 获取消息
                msg = dis.readUTF();
                System.out.println(msg);
            }
            // 4. 释放资源
            dis.close();
            dos.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
