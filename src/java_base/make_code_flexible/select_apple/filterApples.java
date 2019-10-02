package java_base.make_code_flexible.select_apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

//        Lambda表达式写法
//        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
    }
}
