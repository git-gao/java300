package com.java.IOStream;

/**
 * 装饰器模式
 * 实现放大器对声音的放大
 */
public class TestDecorate01 {
    public static void main(String[] args) {
        Person person = new Person();
        person.say();

        // 装饰
        Amplifier amplifier = new Amplifier(person);
        amplifier.say();
    }
}

/**
 * 抽象组件
 */
interface Say {
    void say();
}

/**
 * 具体组件
 */
class Person implements Say {

    //属性
    private int voice = 10;

    @Override
    public void say() {
        System.out.println("人的声音为：" + this.voice);
    }

    public int getVoice() {
        return voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }
}

/**
 * 装饰类
 */
class Amplifier implements Say {

    private Person person = null;

    public Amplifier(Person person) {
        this.person = person;
    }

    @Override
    public void say() {
        System.out.println("人的声音为：" + person.getVoice() * 100 + ", 噪音");
    }
}