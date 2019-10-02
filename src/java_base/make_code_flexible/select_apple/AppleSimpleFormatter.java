package java_base.make_code_flexible.select_apple;

/**
 * @Author: yk
 * @Date: 2019/10/1 21:31
 */
public class AppleSimpleFormatter implements AppleFormatter {

    @Override
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }
}
