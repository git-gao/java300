package com.java.network.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * 1. 使用 DatagramSocket 指定端口，创建发送端
 * 2. 准备数据，转成字节数组
 * 3. 封装成 DatagramPacket 包裹，需要指定目的地
 * 4. 发送包裹 send(DatagramPacket p)
 * 5. 释放资源
 */
public class UdpClient {
    public static void main(String[] args) throws IOException {
        System.out.println("发送方启动中...");
        //1. 使用 DatagramSocket 指定端口，创建发送端
        DatagramSocket client = new DatagramSocket(8080);
        //2. 准备数据，转成字节数组
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String data = reader.readLine();
            byte[] datas = data.getBytes();
            //3. 封装成 DatagramPacket 包裹，需要指定目的地，端口与接收方端口一致
            SocketAddress socketAddress = new InetSocketAddress("localhost", 8989);
            DatagramPacket packet = new DatagramPacket(datas, 0, datas.length, socketAddress);
            // 4. 发送包裹 send(DatagramPacket p)
            client.send(packet);
            if (data.equals("bye")) {
                break;
            }
        }

        //释放资源
        client.close();

    }
}
