package java_base.make_code_flexible.select_apple;

/**
 * @Author: yk
 * @Date: 2019/9/30 16:29
 */
public class Apple {

    private Integer weight;
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Apple(Integer weight, String color) {
        this.weight = weight;
        this.color = color;
    }

}
