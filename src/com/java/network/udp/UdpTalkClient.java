package com.java.network.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * UDP 通信多次交流，客户端封装
 * 使用面向对象封装
 */
public class UdpTalkClient implements Runnable{

    private DatagramSocket client;
    private BufferedReader reader;
    private String toIP;
    private int toPort;

    public UdpTalkClient(int port, String toIP, int toPort) {
        System.out.println("发送方启动中...");
        this.toIP = toIP;
        this.toPort = toPort;
        try {
            client = new DatagramSocket(port);
            reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String data = reader.readLine();
                byte[] datas = data.getBytes();
                //3. 封装成 DatagramPacket 包裹，需要指定目的地，端口与接收方端口一致
                SocketAddress socketAddress = new InetSocketAddress(toIP, toPort);
                DatagramPacket packet = new DatagramPacket(datas, 0, datas.length, socketAddress);
                // 4. 发送包裹 send(DatagramPacket p)
                client.send(packet);
                if (data.equals("bye")) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 释放资源
        client.close();
    }
}
