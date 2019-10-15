package java_base.make_code_flexible.streamAPI.studyForCollectorInterface.caseOne;

import java_base.make_code_flexible.streamAPI.commonFunctionForStream.Dish;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/10/14 19:38
 */
public class collectDish {
    public static void main(String[] args) {

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


        // 标准实现： List<Dish> dish = menu.stream().collect(toList());
        // 区别： 原生toList是一个工厂，而toListCollector必须用new实例化
        List<Dish> dishes = menu.stream().collect(new ToListCollector<Dish>());
        System.out.println("用toListCollector收集器打印菜单： " + dishes);
    }
}
