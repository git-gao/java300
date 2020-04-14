package com.java.network.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室：服务端
 * 每个客户必须等待之前的客户退出才能继续排队
 */
public class MultiChat {

    public static void main(String[] args) {
        System.out.println("-------------Server------------");
        try {
            // 1. 指定端口
            ServerSocket server = new ServerSocket(8080);
            // 2. 阻塞式接收
            Socket client = server.accept();
            System.out.println("一个客户端创建了连接");
            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            // 3. 接收消息
            boolean isRunning = true;
            while (isRunning) {
                String msg = dis.readUTF();
                // 4. 返回消息
                dos.writeUTF(msg);
                dos.flush();
            }
            // 5. 释放资源
            dis.close();
            dos.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
