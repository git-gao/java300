线程状态

1. 新生态(NEW)：new 一个对象时，线程拥有一个工作空间，跟主存进行交互
2. 就绪态(Runnable)：a.线程对象调用 start() 方法进入就绪态，表示线程具备多线程条件
          b.线程阻塞状态解除进入就绪态，sleep 时间到了，join()执行完了，阻塞解除
          c.yield 让出CPU调度进入就绪态
          d.jvm 切换
3. 运行态(Runnable)：
4. 阻塞态(Blocked, waiting, timed waited)：1) sleep, 2) join, 3) wait, 4) IO流 read,write
5. 死亡态(Terminated)：run() 方法执行完毕
