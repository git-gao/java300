package com.java.interview.containers;

import java.util.Random;

public class Weather {
    private static Random random = new Random(10);
    private boolean shadow = random.nextDouble() > 0.5;
    public String toString() {
        if (shadow) {
            return "明天将会下雨";
        } else {
            return "明天阳光明媚";
        }
    }
}
