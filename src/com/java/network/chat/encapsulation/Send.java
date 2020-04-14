package com.java.network.chat.encapsulation;

import java.io.*;
import java.net.Socket;

/**
 * 使用多线程封装了发送端
 * 1. 发送消息
 * 2. 从控制台获取消息
 * 3. 释放资源
 * 4. 重写 run()
 */
public class Send implements Runnable {

    private BufferedReader console;
    DataOutputStream dos;
    private Socket client;
    private boolean isRunning;
    private String name;

    public Send(Socket client, String name) {
        this.client = client;
        this.name = name;

        try {
            console = new BufferedReader(new InputStreamReader(System.in));
            dos = new DataOutputStream(client.getOutputStream());
            // 发送名字
            send(name);
            isRunning = true;
        } catch (IOException e) {
            System.out.println("客户端异常");
            release();
        }
    }

    // 获取消息
    private String getStrFromConsole() {
        try {
            return console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    // 发送消息
    private void send (String msg) {
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException e) {
            System.out.println("发送消息异常");
            release();
        }
    }

    // 释放资源
    private void release () {
        this.isRunning = false;
        CloseUtils.close(dos, client);
    }

    @Override
    public void run() {
        while (isRunning) {
            String msg = getStrFromConsole();
            if (!msg.equals("")) {
                send(msg);
            }
        }
    }

}
