package java_base.make_code_flexible.select_apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

/**
 * @Author: yk
 * @Date: 2019/9/30 17:08
 */
public class filterApples {
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }

        return result;
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "red"), new Apple(190, "red"));
        List<Apple> heavyApples = filterApples(inventory, new AppleRedAndHeavyPredicate());

        //Lambda表达式
        List<Apple> result = filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()));

        prettyPrintApple(heavyApples, new AppleFancyFormatter());
        prettyPrintApple(heavyApples, new AppleSimpleFormatter());
        prettyPrintApple(result, new AppleSimpleFormatter());

        // 排序方法一： 匿名类
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        // 排序方法二： Lambda表达式常规写法
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

        // 排序方法三： Lambda表达式易读写法
        // （使用Comparator的静态辅助方法comparing，可接受Function来提取Comparable键值并生成Comparator对象）
        inventory.sort(comparing((a) -> a.getWeight()));

        // 排序方法四（最优）： Lambda表达式的方法引用写法  (Apple::getWeight 相当于 (Apple a) -> a.getWeight())
        // 逆序排列：
        //     inventory.sort(comparing(Apple::getWeight).reversed());
        inventory.sort(comparing(Apple::getWeight));

        /** 比较器链 -- 第一个条件相等后进一步比较排序：
         *    inventory.sort(comparing(Apple::getWeight)
         *             .reversed()
         *             .thenComparing(Apple::getCountry));
         *  谓词复合 (优先级从左到右)：
         *     Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(a -> a.getWeight() > 150)
         *                                                        .or(a -> "green".equals(a.getColor()));
         *  函数复合
         *     Function<Integer, Integer> f = x -> x + 1;
         *     Function<Integer, Integer> g = x -> x * 2;
         *     Function<Integer, Integer> h = f.andThen(g);    //先f后g
         *     int result = h.apply(1);                       //返回4
         *     Function<Integer, Integer> w = f.compose(g);   //先g后f
         *     int result = w.apply(1);                       //返回3
         */

    }
}
