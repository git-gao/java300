package com.java.network.udp;

public class TalkTeacher {

    public static void main(String[] args) {
        // 接收
        new Thread(new UdpTalkServer(9999, "学生")).start();
        // 发送
        new Thread(new UdpTalkClient(5555, "localhost", 7777)).start();
    }
}
