package com.java.IOStream.readerWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 文件字符输出流测试
 */
public class TestFileWriter {

    public static void main(String[] args) throws IOException {

        File dir = new File("F:\\ideaWorkspace\\java300\\test\\IOstream\\dir\\readerWriter");
        dir.mkdirs();// 创建文件夹
        File file = new File(dir + "/test2.txt");
        file.createNewFile();// 创建文件

        //File src = new File(file.getAbsolutePath());

        Writer writer = null;

        try {
            writer = new FileWriter(file);
            // 写入，写法一
            String msg = "mnbvcxz kkk 好好学习天天向上\r\n";
            char[] datas = msg.toCharArray();
            writer.write(datas, 0, datas.length);

            // 写入，写法二
            String msg1 = "mnbvcxz kkk 好好学习天天向上\r\n";
            writer.write(msg);

            // 写入，写法三
            writer.append("121231").append("好好学习天天向上");
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
