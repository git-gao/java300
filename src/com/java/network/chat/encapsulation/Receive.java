package com.java.network.chat.encapsulation;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 使用多线程封装了接收端
 * 1. 接收消息
 * 2. 释放资源
 * 3. 重写 run()
 */
public class Receive implements Runnable {
    private DataInputStream dis;
    private Socket client;
    private boolean isRunning;


    public Receive(Socket client) {
        this.client = client;

        try {
            dis = new DataInputStream(client.getInputStream());
            isRunning = true;
        } catch (IOException e) {
            System.out.println("接收端异常");
            release();
        }
    }

    private String receive () {
        String msg = "";
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
            System.out.println("接收消息异常");
            release();
        }

        return msg;
    }

    // 释放资源
    private void release () {
        this.isRunning = false;
        CloseUtils.close(dis, client);
    }

    @Override
    public void run() {
        while (isRunning) {
            String msg = receive();
            if (!msg.equals("")) {
                System.out.println(msg);
            }
        }
    }
}
