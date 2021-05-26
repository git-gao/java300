package com.java.interview;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;

public class HashCodeTest {

    public static void main(String[] args) {
        String str = "gy11031995GY";
        String s = DigestUtils.md5Hex(str);
        System.out.println(s);

        HashMap hashMap = new HashMap();

    }
}


