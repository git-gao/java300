### final 关键字

---
**1. final 修饰变量，则变量不可被修改**

    final static int i = 1;
    eg: i = 0; -> Cannot assign a value to final variable 'i'
---    
**2. final 修饰方法，则方法不可被子类重写，但是可以进行方法重载**

    a.子类继承父类方法示例代码
    class Animal {
        public final void eat() {
            System.out.println("动物都能吃东西");
        }
    }
    
    class doc extends Animal {
        public void eat() {
    
        }
    } 
    子类重写 eat 方法时提示继承的 Animal 类的方法是 final 修饰的。
    'eat()' cannot override 'eat()' in 'Animal'; overridden method is final
    
    b.如果对 final 修饰的 eat 方法进行重载则可行，不会报错
    class Animal {
        public final void eat() {
            System.out.println("动物都能吃东西");
        }
        
        public void eat(String foot) {
            System.out.println("可以吃" + foot);
        }
    }
 ---
 **3.final 修饰类，则类不可被继承**
    
    final class Animal {
        
    }
    
    class doc extends Animal {
    
    }
    final 修饰的 Animal 类，子类无法继承
    Cannot inherit from final 'com.java.keywords.Animal'