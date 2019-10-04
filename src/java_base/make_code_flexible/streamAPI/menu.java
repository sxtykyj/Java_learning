package java_base.make_code_flexible.streamAPI;

import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/10/4 12:45
 */
public class menu {
    List<Dish> dishes;

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public menu(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
