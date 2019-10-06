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
                        // 根据流所生成元素的hashcode和equals方法实现，去掉重复数据
                        .distinct()
                        // 处理流水线，赋map值给lowCaloricDishesName，并转换成list返回结果，关闭流
                        .collect(toList());
        /**
         * Array.stream(arr)方法可以接受一个数组并产生一个流
         * 其他流的常用方法：
         *    .skip（n）：跳过前n个符合条件的数据
         *    .map(Array::stream): 让每个数组变成一个单独的流
         *    .flatMap(Array::stream): 让每个数组映射成流的内容，即会合并上一个方法生成的所有单个流为一个流
         *    .allMatch(condition): 流中是否有一个元素能匹配，返回boolean
         *    .anyMatch(condition): 是否有匹配流中所有元素，返回boolean
         *    .noneMatch(condition): 是否流中所有元素都不匹配，返回boolean
         *    .findFirst() /  .findAny()
         *
         * 对流中所有元素求和，sum初始值为0
         *    int sum = numbers.stream().reduce(0, (a,b) -> a + b)；
         *    简化版： int sum = numbers.stream().reduce(0, Integer::sum)；
         *            //sum, max, min   <int max = maxCalories.orElse(1); 加默认值>
         *
         * 优化装箱造成的复杂性：
         *    .map(Dish::getCalories).reduce(0, Integer::sum)；  =  .mapToInt(Dish::getCalories).sum()；
         *
         * 转换回对象流：
         *     IntStream intStream = menu.stream().mapToInt(Dish::getCalories);    //将stream转换成数值流
         *     Stream<Integer> stream = intStream.boxed();                         //将数值转回stream
         *
         * 数值范围：
         *      .range(1, 100)           不包括100
         *      .rangeClosed(1, 100)     包括100
         *     IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0)；
         *     System.out.println(evenNumbers.count());
         *
         *
         */
        System.out.println(lowCaloricDishesName);
    }
}
