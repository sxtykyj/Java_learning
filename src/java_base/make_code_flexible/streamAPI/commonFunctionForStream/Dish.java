package java_base.make_code_flexible.streamAPI.commonFunctionForStream;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yk
 * @Date: 2019/10/4 12:57
 */
public class Dish {
    String name;
    boolean vegetarian;
    Integer calories;
    Type type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Dish(String name, boolean vegetarian, Integer calories, Type type) {
        this.calories = calories;
        this.type = type;
        this.name = name;
        this.vegetarian = vegetarian;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * 菜肴类型枚举
     */
    public enum Type {
        MEAT,
        FISH,
        OTHER
    }

    /**
     * 菜肴卡路里分类枚举
     */
    public enum CaloriesLevel {
        DIET(400),
        NORMAL(700),
        FAT(2000);

        int threshold;

        CaloriesLevel(int threshold) {
            this.threshold = threshold;
        }

        public static Map<Integer, CaloriesLevel> caloriesLevelMap = new HashMap<>();

        static {
            for (CaloriesLevel caloriesLevel : CaloriesLevel.values()) {
                caloriesLevelMap.put(caloriesLevel.threshold, caloriesLevel);
            }
        }

        // 按卡路里分类返回菜肴类型
        public static CaloriesLevel valueOfByLevel(Integer value) {
            if (value <= DIET.threshold) {
                return DIET;
            } else if (value <= NORMAL.threshold) {
                return NORMAL;
            } else {
                return FAT;
            }
        }
    }
}
