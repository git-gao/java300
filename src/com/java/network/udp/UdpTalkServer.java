package com.java.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * UDP 通信多次交流，服务端封装
 */
public class UdpTalkServer implements Runnable{

    private DatagramSocket server;
    private String from;
    private byte[] container;
    private static final int SIZE = 1024;

    public UdpTalkServer(int port, String from) {
        this.from = from;
        System.out.println("接收方启动中...");
        try {
            server = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 2. 准备容器 封装成 DatagramPacket 包裹
                container = new byte[SIZE];
                DatagramPacket packet = new DatagramPacket(container, 0, container.length);
                // 阻塞式接收数据
                server.receive(packet);
                byte[] datas = packet.getData();
                int len = packet.getLength();
                String data = new String(datas, 0, len);
                if (data.equals("bye")) {
                    break;
                }
                System.out.println(from + ": " + data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        server.close();
    }
}
