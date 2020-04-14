package com.java.network.chat.encapsulation;

import java.io.Closeable;
import java.io.IOException;

/**
 * 释放资源工具类
 */
public class CloseUtils {

    public static void close(Closeable... targets) {
        for (Closeable target: targets) {

            try {
                if (null != target) {
                    target.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
