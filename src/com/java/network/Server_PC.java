package com.java.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.imageio.stream.FileImageInputStream;


public class Server_PC {

    public static void main(String[] args) throws Exception {
        ServerSocket server =  new ServerSocket(8888);
        while(true){
            Socket socket = server.accept();
            InputStream is = socket.getInputStream();

            String methodLine = readLine(is, 0);//取得请求方式行
            System.out.println(methodLine);//头信息第一行
            String method = new StringTokenizer(methodLine).nextElement().toString();//取得请求方法
            String lu_jin = null;//请求到路径
            String params_line = null;//参数行，get和post取参方式不一样，get在url里面取就行，post在读完头信息后，在消息体里面取
            if("POST".equals(method)){
                String lineStr;
                int content_length = 0;//post传入到参数行字节码个数
                do{
                    lineStr = readLine(is, 0);//取得行字符串信息
                    if(lineStr.indexOf("Content-Length") != -1){
                        content_length = Integer.parseInt(lineStr.split(":")[1].trim());
                    }
                }while(!lineStr.equals("\r\n"));//将头文件读完
                params_line = readLine(is, content_length);//读完头文件后，再读取post传过来到消息体
                lu_jin = methodLine.split(" ")[1];//即取得“http://127.0.0.1:8888/路径” 中的  “/路径”
            }else if("GET".equals(method)){
                int index = methodLine.indexOf("?");
                params_line = methodLine.split(" ")[1];//即取得“http://127.0.0.1:8888/路径?参数” 中的“/路径?参数”
                if(index != -1){//如果带参情况
                    lu_jin = params_line.substring(0,index);
                    params_line = params_line.substring(index+1);
                }else{
                    lu_jin = params_line;
                }
            }
            System.out.println("lu_jin:---"+lu_jin);
            System.out.println("params_line:---"+params_line);

			/*
			//用二进制流将数据输出到浏览器，可用于文件、页面显示等
			PrintStream ps = new PrintStream(socket.getOutputStream());
			ps.println("HTTP/1.1 200 OK");//头信息
			ps.println("Accept-ranges:bytes");//头信息，接受范围
			ps.println("Content-Type:*");//头信息
			ps.println("Access-Control-Allow-Origin:*");//头信息，跨越
			ps.println();//告诉浏览器头信息结束，下面开始输出二进制流

            File file = new File("C:\\Users\\Administrator\\桌面\\t0122662127e2518bcb.jpg");
            InputStream input = new FileInputStream(file);
			byte[] b = new byte[(int) file.length()];
			input.read(b);
			//ps.write(b);//向浏览器输出二进制流，在浏览器访问http://127.0.0.1:8888
			ps.write("请求成功".getBytes());//向浏览器输出二进制流，在浏览器访问http://127.0.0.1:8888
			ps.close();
			is.close();
			socket.close();
			*/

            //向浏览器输入文本
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("HTTP/1.1 200 OK");//头信息
            pw.println("Content-Type:*");//头信息，接受范围，如果返回json数据必须是Content-Type:appllication/json
            pw.println("Access-Control-Allow-Origin:*");//头信息，跨越
            pw.println();//告诉浏览器头信息结束，下面开始输出文本
			/*
            pw.println("你好！");
            pw.println("你成功了吗");
            pw.println("到底成功了没有");//向浏览器输出文本，在浏览器访问http://127.0.0.1:8888
            */
            Map<String, String> map = new HashMap<String, String>();
            map.put("a", "123");
            map.put("b", "456");
            map.put("c", "789");
            pw.println("1234568");//向浏览器输出二进制流，用ajax请求http://127.0.0.1:8888，返回json数据，头信息必须是Content-Type:appllication/json
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("登陆成功！");
            pw.close();
            is.close();

            socket.close();

        }


    }

    public static String readLine(InputStream is, int total) throws Exception{
        ArrayList lineByteList = new ArrayList();
        byte bt;
        int index = 0;
        if(total == 0){
            do{
                bt = (byte) is.read();
                lineByteList.add(bt);
            }while(bt != 10 && bt != -1);
        }else{
            do{
                bt = (byte) is.read();
                lineByteList.add(bt);
                total --;
            }while(total > 0);

        }
        byte[] lineByteArr = new byte[lineByteList.size()];
        for(int i=0;i < lineByteArr.length; i++){
            lineByteArr[i] = (byte) lineByteList.get(i);
        }
        lineByteList.clear();
        String str = new String(lineByteArr,"GBK");
        if (str.startsWith("Referer")) {//如果有Referer头时，使用UTF-8编码
            str = new String(lineByteArr, "UTF-8");
        }
        return str;
    }
}