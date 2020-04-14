package com.java.network.chat.encapsulation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 在线聊天室：服务端
 * 使用多线程，封装了服务端，实现多个客户可以正常收发消息
 * 问题：
 * 1. 代码太多不易维护
 * 2. 客户端没有分开，必须先写后读
 */
public class PackMultiChat {

    static CopyOnWriteArrayList<Channel> all = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        System.out.println("-------------Server------------");
        try {
            // 1. 指定端口
            ServerSocket server = new ServerSocket(8080);
            while (true) {
                Socket clinet = server.accept();
                System.out.println("一个客户端建立连接");
                Channel channel = new Channel(clinet);
                all.add(channel);// 管理全部成员
                new Thread(channel).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Channel implements Runnable{
        private DataInputStream dis;
        private DataOutputStream dos;
        private Socket client;
        private boolean isRunning;
        private String name;

        public Channel(Socket client) {
            this.client = client;
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
                isRunning = true;
                this.name = receive();
                // 欢迎您的到来
                this.send("欢迎您的到来");
                sendOthers(this.name + "进入了聊天室", true);
            } catch (IOException e) {
                // 异常释放资源
                release();
            }
        }

        // 接收消息
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

        /**
         * 群聊：获取自己的消息，发送给其他人
         * 私聊：约定数据格式 @xxx:msg
         *
         * @param msg
         */
        public void sendOthers(String msg, boolean isSysMsg) {
            boolean isPrivate = msg.startsWith("@");
            if (isPrivate) {
                int idx = msg.indexOf(":");
                String targetName = msg.substring(1, idx);
                msg = msg.substring(idx+1);

                for (Channel other : all) {
                    if (other.name.equals(targetName) && this.name != other.name) {
                        other.send(this.name + "私聊您：" + msg);// 私聊消息
                    }
                }
            } else {
                for (Channel other : all) {
                    if (other == this) {
                        continue;
                    }
                    if (!isSysMsg) {
                        other.send(this.name + "对所有人说：" + msg);// 群聊消息
                    } else {
                        other.send(msg); // 系统消息
                    }
                }
            }
        }
        // 释放资源
        private void release () {
            this.isRunning = false;
            CloseUtils.close(dis, dos, client);
            // 退出
            all.remove(this);
            sendOthers(this.name+ "退出了群聊", true);
        }

        @Override
        public void run() {
            while (isRunning) {
                String msg = receive();
                if (!msg.equals("")) {
                    //send(msg);
                    sendOthers(msg, false);// 群聊
                }
            }
        }
    }
}
