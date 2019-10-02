package java_base.make_code_flexible.select_apple;

import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/9/30 19:07
 */
public class AppleRedAndHeavyPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return "red".equals(apple.getColor()) && apple.getWeight() > 150;
    }

}
