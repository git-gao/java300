package com.java.interview;

/**
 * equlas 与 == 的比较
 */
public class EqualsTest {

    public static void main(String[] args) {

        int a = 1;
        int b = 1;
        System.out.println("a == b => " + (a==b)); // 基本数据类型比较的是值是否相等
        String str1 = "aaa";
        String str2 = "aaa";
        String str3 = new String("aaa");
        String str4 = new String("aaa");
        System.out.println("str1==str2 => " + (str1==str2));
        System.out.println("str3==str4 => " + (str3==str4));
        System.out.println("str1==str3 => " + (str1==str3));
        System.out.println("str1.EqualsTest(str2) => " + (str1.equals(str2)));
        System.out.println("str3.EqualsTest(str4) => " + (str3.equals(str4)));
        System.out.println("str1.EqualsTest(str3) => " + (str1.equals(str3)));

        String str5 = str1;
        System.out.println("str1==str5 => " + (str1==str5));
        String str6 = str3;
        System.out.println("str3==str6 => " + (str3==str6));

        System.out.println("str2 hashcode = " + str2.hashCode());
        System.out.println("str3 hashcode = " + str3.hashCode());
        System.out.println("str2 identityHashCode = " + System.identityHashCode(str2));
        System.out.println("str3 identityHashCode = " + System.identityHashCode(str3));

        Integer c = 1;
        Integer d = 1;
        Integer e = new Integer(1);
        Integer f = new Integer(1);
        System.out.println("c==d => " + (c==d));
        System.out.println("e==f => " + (e==f));
        System.out.println("c==e => " + (c==e));
        System.out.println("c.EqualsTest(d) => " + (c.equals(d)));
        System.out.println("e.EqualsTest(f) => " + (e.equals(f)));
        System.out.println("c.EqualsTest(e) => " + (c.equals(e)));

        int g = 128;
        Integer h = 128;
        Integer i = 128;

        System.out.println("g==h => " + (g==h));
        System.out.println("h==i => " + (h==i));
        System.out.println("h.EqualsTest(i) => " + (h.equals(i)));


    }
}
