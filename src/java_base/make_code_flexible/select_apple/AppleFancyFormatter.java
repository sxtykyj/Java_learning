package java_base.make_code_flexible.select_apple;

/**
 * @Author: yk
 * @Date: 2019/10/1 21:27
 */
public class AppleFancyFormatter implements AppleFormatter {

    @Override
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
        return "A " + characteristic + " " + apple.getColor() + " apple";
    }
}
