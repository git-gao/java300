package com.java.interview.algorithm;

public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(new Fibonacci().fib_recurse(3));
        System.out.println(new Fibonacci().fib(10));
        System.out.println(new Fibonacci().fib_point(10));
    }

    /**
     * 递归
     * @param n
     * @return
     */
    public int fib_recurse(int n) {
        if (n == 0 || n == 1)
            return n;
        return fib(n-1) + fib(n-2);
    }

    /**
     * 去重递归
     * @param n
     * @return
     */
    public int fib(int n) {
        int[] arr = new int[n+1];
        return recurse(arr,n);
    }

    public static int recurse(int[] arr, int n) {
        if (n == 0 || n == 1)
            return n;
        if (arr[n] != 0) {
            return arr[n];
        }

        arr[n] = recurse(arr, n-1) +  recurse(arr, n-2);
        return arr[n];
    }

    /**
     * 双指针
     * @param n
     * @return
     */
    public int fib_point(int n) {
        if (n == 0 || n == 1)
            return n;
        int low =0,high = 1,sum=1;
        for (int i = 2; i <= n; i++) {
            sum = low + high;
            low = high;
            high = sum;
        }

        return high;
    }
}
