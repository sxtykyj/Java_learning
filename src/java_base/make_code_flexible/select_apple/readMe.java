package java_base.make_code_flexible.select_apple;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/10/1 22:49
 */
public class readMe {

    /**
     * 1. 灵活编程1：行为参数化 = 类 + 匿名类 + Lambda
     * <p>
     *
     * 该例子进一步 -> filterApples适用类型抽象化:
     *     public interface Predicate<T> {
     *         boolean test(T t);
     *     }
     *
     *     public static <T> List<T> filter(List<T> list, Predicate<T> p) {
     *         List<T> result = new ArrayList<>();
     *         for (T e : list) {
     *             if (p.test(e)) {
     *                 result.add(e);
     *             }
     *         }
     *         return result;
     *     }
     *
     *     List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
     *
     *     2. Java8的Lambda表达式    (T,U) -> R
     *        1） Predicate<T>   T - > boolean
     *            定义接口：java.util.function.Predicate<T>
     *            抽象方法：test（）
     *            使用方法：Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
     *                      List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
     *        2） Consumer<T>    T - > void
     *            定义接口：java.util.function.Consumer<T>
     *            抽象方法：accept（）
     *            使用方法：forEach(
     *                            Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> System.out.println(i)
     *                      );
     *        3) Function<T, R>    T - > R
     *           定义接口：java.util.function.Function<T, R>
     *           抽象方法：apply()
     *           使用方法：List<Integer> l = map(
     *                                          Arrays.asList("lambda", "in", "action"), (String s) -> s.length()
     *                                    );
     *
     */


}
