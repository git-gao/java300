package com.java.interview.containers;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class WeatherDetector {
    public static <T extends Ant> void detectWeather(Class<T> type) throws Exception {
        Constructor<T> ant = type.getConstructor(int.class);
        Map<Ant, Weather> map = new HashMap<Ant, Weather>();
        for (int i = 0; i < 5; i++) {
            map.put(ant.newInstance(i), new Weather());
        }
        System.out.println("map =" + map);
        Ant gh = ant.newInstance(3);
        System.out.println("查找预测天气: " + gh);
        if (map.containsKey(gh)) {
            System.out.println(map.get(gh));
        } else {
            System.out.println("找不到 key: " + gh);
        }
    }

    public static void main(String[] args) throws Exception {
        detectWeather(Ant.class);
    }
}
