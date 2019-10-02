package java_base.make_code_flexible.select_apple;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/10/1 22:49
 */
public class readMe {

    /**
     * 灵活编程1：行为参数化 = 类 + 匿名类 + Lambda
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
     */


}
