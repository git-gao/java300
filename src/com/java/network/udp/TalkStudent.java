package com.java.network.udp;

public class TalkStudent {

    public static void main(String[] args) {
        // 发送方
        new Thread(new UdpTalkClient(8888, "localhost", 9999)).start();
        // 接收
        new Thread(new UdpTalkServer(7777, "老师")).start();
    }
}
