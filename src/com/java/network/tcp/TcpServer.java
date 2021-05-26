package com.java.network.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP 服务端建立
 * 双向通信
 */
public class TcpServer {

    public static void main(String[] args) throws IOException {
        // 1. 指定端口，使用 ServerSocket 创建服务器
        ServerSocket server = new ServerSocket(8080);
        // 2. 侦听要连接到此套接字并接受它,阻塞式等待
        Socket client = server.accept();
        System.out.println("一个客户端创建了连接");
        // 3. 操作输入输出
        // 输出
        String ip = client.getInetAddress().getHostAddress();
        InputStream dis = client.getInputStream();
        byte[] buf = new byte[1024];
        int len = dis.read(buf);
        String text = new String(buf,0,len);
        System.out.println(ip+":"+text);
        String uname = "";
        String password = "";
        //DataInputStream dis = new DataInputStream(client.getInputStream());
        //String[] dataArray = dis.readUTF().split("&");
        String[] dataArray = text.split("&");
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
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        if (uname.equals("gao") && password.equals("123")) {
            dos.writeUTF("登陆成功！");
        } else {
            dos.writeUTF("用户名或密码错误！");
        }
        dos.flush(); // 刷新此数据输出流
        // 4. 释放资源
        dis.close();
        client.close();

        server.close();
    }
}
