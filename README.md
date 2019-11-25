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
