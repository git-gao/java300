package com.java.network.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP,客户端建立
 * 双向通信
 */
public class TcpClient {

    public static void main(String[] args) throws IOException {
        // 1. 创建连接
        Socket client = new Socket("localhost", 8080);
        // 2. 操作输入输出
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        // 控制台输入数据
        // 输入
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入用户名:");
        String uname = reader.readLine();
        System.out.println("请输入用户名:");
        String password = reader.readLine();
        String data = uname + "&" +password;
        dos.writeUTF(data);
        dos.flush();
        // 输出
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String result = dis.readUTF();
        System.out.println(result);
        // 3. 释放资源
        dos.close();
        client.close();
    }
}
