### 包装类，装箱和拆箱

包装类型是一种引用数据类型

1.自动装箱和自动拆箱
    
    Integer i = 100; // 自动装箱，Integer i = Integer.valueOf(100);
    int j = i;       // 自动拆箱, int j = i.intValue();

2.包装类型的比较

    a.
    Integer integer1 = new Integer(1024);
    Integer integer2 = new Integer(1024);
    System.out.println(integer1 == integer2); // false
    System.out.println(integer1.equals(integer2)); // true
    
    b.
    Integer integer3 = 1024;
    Integer integer4 = 1024;
    System.out.println(integer3 == integer4); // false
    
    c.
    Integer integer3 = 127;
    Integer integer4 = 127;
    System.out.println(integer3 == integer4); // true

**为什么 b组和c组比较结果不一样？**

定义 Integer integer3 = 127; 时会自动装箱，调用Integer.valueOf() 方法
Integer 的 valueOf 源码如下

    static {
        int var0 = 127;

        high = var0;
        cache = new Integer[high - -128 + 1];
        var2 = -128;

        for(int var3 = 0; var3 < cache.length; ++var3) {
            cache[var3] = new Integer(var2++);
        }
    }

    public static Integer valueOf(int var0) {
        return var0 >= -128 && var0 <= Integer.IntegerCache.high ? Integer.IntegerCache.cache[var0 + 128] : new Integer(var0);
    }
首先检查数字是否在[-128 - 127] 之间，如果在这个范围，则直接从缓存数组 cache 中获取创建好的对象
如果不在这个范围，则创建新的 Integer 对象

所以，127=127 时，从数组取出同一个对象，内存地址一样；而 1024 > 127 ,创建新的对象，内存地址不一样

    
    

3.基本数据类型转化为包装类型

    Integer a = new Integer(1);
    Integer b = Integer.valueOf(1);
    
4.包装类型转化为基本数据类型

    int c = a.intValue();
    Long d = b.longValue();
    System.out.println(c); // 1
    System.out.println(d); // 2
    
5.字符转转化为包装类

    Integer e = new Integer("123");
    Integer f = Integer.parseInt("456");
    System.out.println(e); //123
    System.out.println(f); // 456