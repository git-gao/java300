package com.java.network.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室：服务端
 */
public class Chat {
    public static void main(String[] args) {
        System.out.println("-------------Server------------");
        try {
            // 1. 指定端口
            ServerSocket server = new ServerSocket(8080);
            // 2. 阻塞式接收
            Socket client = server.accept();
            System.out.println("一个客户端创建了连接");
            // 3. 接收消息
            DataInputStream dis = new DataInputStream(client.getInputStream());
            String msg = dis.readUTF();
            // 4. 返回消息
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF(msg);
            dos.flush();
            // 5. 释放资源
            dis.close();
            dos.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
