package com.java.network.tcp;

import java.io.*;
import java.net.Socket;

/**
 * 模拟多个客户端发送
 * 多线程
 */
public class LoginMultiClient {

    public static void main(String[] args) throws IOException {
        // 1. 创建连接
        Socket client = new Socket("localhost", 8080);
        System.out.println("---------------Client---------");
        // 2. 操作输入输出
        // 发送
        new Send(client).send();
        // 接收
        new Receive(client).receive();

        // 3. 释放资源
        client.close();
    }

    /**
     * 发送数据
     */
    static class Send {
        private Socket client;
        private DataOutputStream dos;
        private BufferedReader reader;
        private String msg;

        public Send(Socket client) {
            this.client = client;
            reader = new BufferedReader(new InputStreamReader(System.in));
            this.msg = init();

            try {
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void send () {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String init() {
            String uname = null;
            String password = null;
            String data = "";
            try {
                System.out.println("请输入用户名:");
                uname = reader.readLine();
                System.out.println("请输入密码:");
                password = reader.readLine();
                data = "uname=" + uname + "&" + "password=" + password;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return data;
        }
    }

    /**
     * 接收数据
     */
    static class Receive {
        private Socket client;
        private DataInputStream dis;

        public Receive(Socket client) {
            this.client = client;
            try {
                dis = new DataInputStream(client.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String receive() {
            String result = "";
            try {
                result = dis.readUTF();
                System.out.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }
    }
}
