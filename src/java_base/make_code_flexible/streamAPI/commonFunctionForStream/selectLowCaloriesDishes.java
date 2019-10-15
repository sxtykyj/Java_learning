package java_base.make_code_flexible.streamAPI.commonFunctionForStream;


import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.reducing;
import static java_base.make_code_flexible.streamAPI.commonFunctionForStream.Dish.CaloriesLevel.valueOfByLevel;

import java.util.*;

/**
 * @Author: yk
 * @Date: 2019/10/4 12:48
 */
public class selectLowCaloriesDishes {

    public static void main(String[] args) {

        /**
         * 流的常用操作：
         *         流：menu.stream()
         *   多核架构：menu.parallelStream()
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
         */
        System.out.println("打印所有菜肴中卡路里最低的三个菜肴名称： " + lowCaloricDishesName);


        /**
         * 用流收集数据: Collectors相关
         */
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        // Collectors.maxBy 和 Collectors.minBy：计算流中的最大或最小值
        // Optional：Java8 引入的容器，可包含也可不包含值
        Optional<Dish> mostCaloriesDish = menu.stream().collect(maxBy(dishCaloriesComparator));
        // Collectors.summingInt: 汇总求和操作
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        // Collectors.averagingDouble: 汇总求平均值操作
        double avgCalories = menu.stream().collect(averagingDouble(Dish::getCalories));
        // 使用summarizingInt工厂方法,打印可得到：IntSummaryStatistics{count=9, sum=4200, min=120, average=466.666667, max=800}
        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println("打印对菜肴卡路里的所有汇总操作： " + menuStatistics);


        /**
         * Collectors.joining工厂类
         *      返回流中每一个对象应用toString方法得到的所有字符串连接成的一个字符串
         *
         * 注：从实际应用和性能来讲，较为推荐使用该工厂类
         */
        String shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println("打印所有菜肴名称： " + shortMenu);


        /**
         * Collectors.reducing工厂方法：实现各个汇总操作的一般化
         * @param 1. 归约操作的起始值，即流中无元素时的默认返回值（可省略）
         * @param 2. 需要转换操作的对象
         * @param 3. BinaryOperator<t> -> 在本例中是对两个int求和
         *                                或求热量最高的菜：(d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2
         * 返回：Optional类型
         */
        // 求菜肴总热量    <可替换为累计函数来简化：reducing(0, Dish::getCalories, Integer::sum)>
        int totalCaloriesForReducingOperation = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println("打印菜肴总热量： " + totalCaloriesForReducingOperation);
        // 求热量最高的菜
        Optional<Dish> mostCaloriesDishForReducingOperation = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println("打印热量最高的菜肴： " + mostCaloriesDishForReducingOperation);
        // 对于求菜肴总热量，最推荐的方法是把流映射到一个intStream，然后调用sum方法
        // 推荐原因：1. 简明易读； 2. 性能最好：intStream可避免自动拆箱操作
        int theBestWayForTotalCalories = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println("最简洁方法打印菜肴总热量： " + theBestWayForTotalCalories);


        /**
         * Collectors.groupingBy工厂方法: 实现分组操作
         */
        // 简单分组
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println("按菜肴类型分类打印： " + dishesByType);
        // 多级分组
        Map<Dish.Type, Map<Dish.CaloriesLevel, List<Dish>>> dishesByTypeCaloriesLevel =
                menu.stream().collect(
                        groupingBy(Dish::getType,
                                groupingBy(dish -> {
                                    if (dish.getCalories() <= 400) {
                                        return Dish.CaloriesLevel.DIET;
                                    } else if (dish.getCalories() <= 700) {
                                        return Dish.CaloriesLevel.NORMAL;
                                    } else {
                                        return Dish.CaloriesLevel.FAT;
                                    }
                                }))
                );
        System.out.println("按菜肴卡路里分类打印： " + dishesByTypeCaloriesLevel);
        // 结合 Lambda表达式 + enum内置valueOfByLevel方法 简化多级分组
        Map<Dish.CaloriesLevel, List<Dish>> dishesByCaloriesLevel = menu.stream().collect(
                groupingBy(dish -> valueOfByLevel(dish.getCalories())));
        System.out.println("简化版--按菜肴卡路里分类打印： " + dishesByCaloriesLevel);
        // 按子组收集数据
        Map<Dish.Type, Long> typesCount = menu.stream().collect(
                groupingBy(Dish::getType, counting())
        );
        System.out.println("打印菜肴每种类型数量： " + typesCount);
        // 改进求卡路里最高菜肴的function：求每种类型里卡路里最高的菜肴
        Map<Dish.Type, Optional<Dish>> mostCaloriesByType =
                menu.stream().collect(
                        groupingBy(Dish::getType, maxBy(dishCaloriesComparator)));
        System.out.println("打印每种类型里卡路里最高的菜肴： " + mostCaloriesByType);
        // 联合mapping使用
        Map<Dish.Type, Set<Dish.CaloriesLevel>> caloriesLevelByType =
                menu.stream().collect(
                        groupingBy(Dish::getType, mapping(
                                dish -> valueOfByLevel(dish.calories), toSet()
                        ))
                );
        System.out.println("打印每种类型菜肴包含的卡路里等级： " + caloriesLevelByType);


        /**
         * Collectors.collectingAndThen：把收集器传回的Optional类型结果转换为另一种类型
         *      Optional::get -> 转换函数：把生成的Optional类型中的值提取出来
         */
        Map<Dish.Type, Dish> mostCaloriesByTypeThroughChangingType =
                menu.stream().collect(
                        groupingBy(Dish::getType, collectingAndThen(
                                maxBy(dishCaloriesComparator), Optional::get)));
        System.out.println("打印每种类型里卡路里最高的菜肴，并转换为Map类型输出： " + mostCaloriesByTypeThroughChangingType);


        /**
         * Collectors.partitioningBy: 分区函数，最多分两组（true or false）
         */
        Map<Boolean, List<Dish>> partitionedMenu =
                menu.stream().collect(partitioningBy(Dish::isVegetarian));
        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        // 相当于:
        //     List<Dish> vegetarianDishes = menu.stream().filter(Dish::isVegetarian).collect(toList());
        System.out.println("打印所有素食： " + vegetarianDishes);
        // 与其他收集器结合使用
        Map<Boolean, Dish> mostCaloriesPartitionedByVgetarian =
                menu.stream().collect(
                        partitioningBy(Dish::isVegetarian,
                                collectingAndThen(
                                        maxBy(comparingInt(Dish::getCalories)),
                                        Optional::get)
                        )
                );
        System.out.println("打印非素食和素食中热量最高的菜肴： " + mostCaloriesPartitionedByVgetarian);


    }
}
