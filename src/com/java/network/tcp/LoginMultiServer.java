package com.java.network.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginMultiServer {

    public static void main(String[] args) throws IOException {
        // 1. 指定端口，使用 ServerSocket 创建服务器
        ServerSocket server = new ServerSocket(8080);
        // 2. 侦听要连接到此套接字并接受它,阻塞式等待
        boolean isRunning = true;
        while (isRunning) {
            Socket client = server.accept();
            System.out.println("------------Server----------");
            new Thread(new Channel(client)).start();
        }
        server.close();
    }

    /**
     * 一个channel 就代表一个客户端
     */
    static class Channel implements Runnable {
        private Socket client;
        // 输入流方法
        private DataInputStream dis;
        // 输出流
        private DataOutputStream dos;
        public Channel(Socket client) {
            this.client = client;
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                release();
            }
        }

        /**
         * 发送数据
         * @return
         */
        private void send (String msg) {
            try {
                dos.writeUTF(msg);
                dos.flush(); // 刷新此数据输出流
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 接收数据
         * @return
         */
        private String receive () {
            String datas = "";
            try {
                datas = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return datas;
        }

        /**
         * 释放资源
         */
        public void release () {
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
        }

        @Override
        public void run() {
            // 3. 操作输入输出
            String uname = "";
            String password = "";
            String[] dataArray = receive().split("&");
            for (String data: dataArray) {
                String[] userInfo = data.split("=");
                if (userInfo[0].equals("uname")) {
                    System.out.println("你的用户名：" + userInfo[1]);
                    uname = userInfo[1];
                } else if (userInfo[0].equals("password")) {
                    System.out.println("你的密码为：" + userInfo[1]);
                    password = userInfo[1];
                }
            }
            // 输入
            if (uname.equals("gao") && password.equals("123")) {
                send ("登陆成功！");
            } else {
                send ("用户名或密码错误！");
            }
            // 4. 释放资源
            release();

        }

    }
}
