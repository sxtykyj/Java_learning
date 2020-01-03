# Java_learning
## 目录
### 1. [make_code_flexible: 行为参数化 + 流](https://github.com/sxtykyj/Java_learning/tree/master/src/java_base/make_code_flexible)
#### 1).  [Java8_Lambda](https://github.com/sxtykyj/Java_learning/blob/master/src/java_base/make_code_flexible/select_apple/readMe.java)
* Predicate<T>   T - > boolean
  * 定义接口：java.util.function.Predicate<T>
  * 抽象方法：test（）
  * 使用方法：
    * Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
    * List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
* Consumer<T>    T - > void
  * 定义接口：java.util.function.Consumer<T>
  * 抽象方法：accept（）
  * 使用方法：
    * forEach(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> System.out.println(i));
* Function<T, R>    T - > R
  * 定义接口：java.util.function.Function<T, R>
  * 抽象方法：apply()
  * 使用方法：
    * List<Integer> l = map(Arrays.asList("lambda", "in", "action"), (String s) -> s.length());
#### 2).  [Java8_Stream](https://github.com/sxtykyj/Java_learning/tree/master/src/java_base/make_code_flexible/streamAPI)
* 获取流： menu.stream()    <多核架构：menu.parallelStream()>
 
* [流的常用方法](https://github.com/sxtykyj/Java_learning/blob/master/src/java_base/make_code_flexible/streamAPI/commonFunctionForStream/selectLowCaloriesDishes.java)

* 用流读取数据: Collector学习实现
   * [Collector学习实现](https://github.com/sxtykyj/Java_learning/blob/master/src/java_base/make_code_flexible/streamAPI/studyForCollectorInterface/caseOne/ToListCollector.java)
   * [Example_judgePrime](https://github.com/sxtykyj/Java_learning/tree/master/src/java_base/make_code_flexible/streamAPI/studyForCollectorInterface/caseTwo)
 
#### 3).  [Example_selectApple](https://github.com/sxtykyj/Java_learning/tree/master/src/java_base/make_code_flexible/select_apple)

### 2. [java_concurrency:  Java并发](https://github.com/sxtykyj/Java_learning/tree/master/src/java_concurrency)
#### 1).  并发相关概念
* Servlet:
  * 大多数Servlet：无状态的 -> 永远是线程安全的
     * 不包含域也没有引用其他类的域
     * 一次特定计算的瞬时状态
     * 唯一地存在本地变量中，只有执行线程才能访问这些存在进程栈中的本地变量
* 原子操作：一个单独的、不可分割的操作
  * 自增操作（++i）-> 复合操作，非原子操作
     * 由三个离散的操作组成（获取当前值；加1；写回新值）
     * 隐患：缺乏同步情况下，多个线程试图同时更新一个计数器（即存在竞争条件）可能会造成一次递增操作的消失
     * 解决方法：锁  
* 锁：可保证可见性和原子性 -> 对于每一个涉及多个变量的不变约束，需要同一个锁保护其所有的变量
  * 内部锁 -> 每一个对象内部都有一个内部锁
    * 特点：
      1. 互斥：同一时间只有一个线程可以进入该锁保护的方法
      2. 是可重进入的reentrancy（基于per-thread）
          * 原理：线程请求一个未被占用的锁时，JVM将记录锁定占有者并将请求计数（acquisition count）记为1。若同一个线程再次请求则递增计数。每次占用线程退出同步块则计数器值递减，为零时锁释放。
          * 优点：方便锁行为封装，避免部分死锁
    * 举例：synchronized块 -> java的内置锁机制，可强制实现原子性
      * 包含两部分：锁对象的引用；锁保护的代码块
      * synchronized方法的锁就是该方法所在的对象本身；而静态的synchronized方法是从Class对象上获取锁  
      * 缺点：内置锁具有互斥锁的作用，同一时间只有一个线程可以进入该锁保护的方法，这会导致性能问题
* volatile变量：同步的弱形式，只能保证可见性 
  * 特点：
      1. 更新对所有线程可见
      2. 不允许重排序
      3. 读一个volatile类型的变量时总会返回某一线程写入的最新值
      4. 不能保证原子性
  * 使用条件：
      1. 写入变量时并不依赖变量的当前值；或能确保只有单一线程修改变量的值
      2. 变量不需要与其他的状态变量共同参与不变约束
      3. 访问变量时，没有其他原因需要加锁
  * 正确使用举例：1. 用于确保所引用对象状态的可见性；2. 用于标识重要的生命周期事件（如初始化或关闭）的发生
#### 2).  设计线程安全的类
* 基本要素:
   1. 确定对象状态是由哪些变量构成的 -> 对象的域
      * n个基本域 -> 域值组成的n元组
      * n个基本域 + 其他对象的引用 -> 域值组成的n元组 + 被引用对象的域
   2. 确定限制状态变量的不变约束
      * 确保在并发访问下保护不变约束 -> 尽量使用final类型的域
   3. 制定一个管理并发访问对象状态的策略
* 实现:
   1. [实例限制 instance confinement](https://github.com/sxtykyj/Java_learning/tree/master/src/java_concurrency/instance_confinement)
      * 将数据封装在对象内部，把对数据的访问限制在对象的方法上，更易确保线程在访问数据时总能获得正确的锁。（如：私有类成员，本地变量，线程等等）
   2. [Java监视器模式](https://github.com/sxtykyj/Java_learning/tree/master/src/java_concurrency/java_monitor_pattern)
   3. [组合 Composition](https://github.com/sxtykyj/Java_learning/tree/master/src/java_concurrency/ImprovedList)
      * 如，向已有类中添加一个原子操作
   4. 同步策略文档化
#### 3).  使用并发容器提高吞吐量
* ConcurrentHashMap
  1. 使用分离锁机制 -> 任意数量读线程可并发访问，读者和写者可并发访问，有限写线程可并发修改
  2. 不提供独占访问加锁
* BlockingQueue
  1. 支持生产者-消费者模型  -> eg.线程池 + 工作队列(有界队列)
* Deque & BlockingDeque -> 双端队列，允许队尾窃取工作
* [利用ConcurrentHashMap缓存](https://github.com/sxtykyj/Java_learning/tree/master/src/java_concurrency/cache)
* 线程池： [使用线程池的WebServer](https://github.com/sxtykyj/Java_learning/tree/master/src/java_concurrency/ThreadPool_WS)
   * 线程池管理一个工作者的同构池（homogeneous pool），与工作队列紧密绑定
   * 优点：
       1. 可重用存在的线程而非创建新线程 -> 可抵消线程创建和消亡产生的开销
       2. 在请求到达时工作者线程通常已存在，因此用于创建线程的等待时间不会延迟任务的执行
   * 改进（相对于Timer）： 
     1. 对于周期性的异步调度任务，可使用调度线程池（ScheduledThreadPoolExecutor）
     2. ScheduledThreadPoolExecutor只支持相对时间（而Timer对调度的支持基于绝对时间）
     3. 需要构建自己的调度任务时可使用类库提供的DelayQueue，它为ScheduledThreadPoolExecutor提供了调度功能
   * 设计线程池
     1. 配置线程池大小：最优池的线程数 = CPU数量 * 目标CPU使用率（0-1之间）* （1 + W/C（等待时间与计算时间的比率））
     2. 选择策略
        * 使用有限队列（ArrayBlockingQueue/ LinkedBlockingQueue/ PriorityBlockingQueue） -> 有助于避免资源耗尽情况发生
        * 使用饱和策略<使用JDK提供的RejectedExecutionHandler实现> -> 有助于解决有限队列队满的情况
           1. 中止策略（abort）：默认策略，会引起executor抛出未检查的RejectedExecutionException，调用者可捕获该异常并且编写处理代码
           2. 遗弃策略（discard）：当新提交的任务不能进入队列等待执行时，该策略会默认放弃该任务
           3. 遗弃最旧的（discard-oldest）：会丢弃接下来会执行的任务，还会尝试重新提交新任务（注意，不可结合优先队列使用，因为该策略会抛弃优先级最高的任务）
           4. 调用者运行（caller-runs）：不会丢弃任务或抛异常，而会将一些任务推回到调用者那里以减缓新任务流
     
     
     
     
     
     
     
     
     
  
