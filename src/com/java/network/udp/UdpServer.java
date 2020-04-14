package com.java.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 同一个协议下 端口不允许冲突
 * 1. 使用 DatagramSocket 指定端口，创建接收端
 * 2. 准备容器 封装成 DatagramPacket 包裹
 * 3. 阻塞式接收包裹 receive(DatagramPacket p)，先开启的server 等待发送方发送数据
 * 4. 分析数据
 *      getData，getLength
 * 5. 释放资源
 */
public class UdpServer {
    public static void main(String[] args) throws IOException {
        System.out.println("接收方启动中...");
        // 1. 使用 DatagramPacket 指定端口，创建接收端
        DatagramSocket server = new DatagramSocket(8989);
        while (true) {
            // 2. 准备容器 封装成 DatagramPacket 包裹
            byte[] container = new byte[1024 * 10];
            DatagramPacket packet = new DatagramPacket(container, 0, container.length);
            // 3. 阻塞式接收包裹 receive(DatagramPacket p)
            server.receive(packet);
            // 4. 分析数据
            byte[] datas = packet.getData();
            int len = packet.getLength();
            String data = new String(datas, 0, len);
            System.out.println(data);
            if (data.equals("bye")) {
                break;
            }

        }
        // 5. 释放资源
        server.close();

    }
}
