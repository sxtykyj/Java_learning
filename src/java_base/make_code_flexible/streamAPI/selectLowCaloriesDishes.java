package java_base.make_code_flexible.streamAPI;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;


import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/10/4 12:48
 */
public class selectLowCaloriesDishes {

    public static void main(String[] args) {

        /**
         * 流：menu.stream()
         * 多核架构：menu.parallelStream()
         */

        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        List<String> lowCaloricDishesName =
                // 从menu中获取流
                menu.stream()
                        // 筛选dashes，返回流
                        .filter(dish -> dish.getCalories() <= 400)
                        // 对筛选后的dishes依照calories排序，返回流
                        .sorted(comparing(Dish::getCalories))
                        // 按排序后的顺序提取dashes对应name属性生成map，返回流
                        .map(Dish::getName)
                        //截断流，只选calories最低的三个dishes，返回流
                        .limit(3)
                        // 处理流水线，赋map值给lowCaloricDishesName，并转换成list返回结果，关闭流
                        .collect(toList());
        System.out.println(lowCaloricDishesName);
    }
}
