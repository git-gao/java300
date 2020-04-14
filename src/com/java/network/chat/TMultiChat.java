package com.java.network.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室：服务端
 * 使用多线程，实现多个客户可以正常收发消息
 * 问题：
 * 1. 代码太多不易维护
 * 2. 客户端没有分开，必须先写后读
 */
public class TMultiChat {

    public static void main(String[] args) {
        System.out.println("-------------Server------------");
        try {
            // 1. 指定端口
            ServerSocket server = new ServerSocket(8080);
            while (true) {
                // 2. 阻塞式接收
                Socket client = server.accept();
                System.out.println("一个客户端创建了连接");
                new Thread(() ->{
                    DataInputStream dis = null;
                    DataOutputStream dos = null;
                    try {
                        dis = new DataInputStream(client.getInputStream());
                        dos = new DataOutputStream(client.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // 3. 接收消息
                    boolean isRunning = true;
                    while (isRunning) {
                        String msg = null;
                        try {
                            msg = dis.readUTF();
                            // 4. 返回消息
                            dos.writeUTF(msg);
                            dos.flush();
                        } catch (IOException e) {
                            //e.printStackTrace();
                            isRunning = false;
                        }

                    }
                    // 5. 释放资源
                    try {
                        if (null != dis) {
                            dis.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (null != dos) {
                            dos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (null != client) {
                            client.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
