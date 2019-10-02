package java_base.make_code_flexible.select_apple;

/**
 * @Author: yk
 * @Date: 2019/9/30 16:37
 */
public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
