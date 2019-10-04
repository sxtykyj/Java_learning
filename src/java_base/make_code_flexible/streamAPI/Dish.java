package java_base.make_code_flexible.streamAPI;

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

    public enum Type {
        MEAT,
        FISH,
        OTHER
    }
}
