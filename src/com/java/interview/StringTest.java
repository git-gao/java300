package com.java.interview;

public class StringTest {

    public static void main(String[] args) {

    }

    public String test4String(String[] arr) {
        String res = "";
        for (int i = 0; i < arr.length; i++) {
            res = res + arr[i];
        }
        return res;
    }

    public String test4StringBuilder(String[] arr) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            res.append(arr[i]);
        }
        return res.toString();
    }
}
