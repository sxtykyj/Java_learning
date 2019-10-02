package java_base.make_code_flexible.select_apple;

/**
 * @Author: yk
 * @Date: 2019/9/30 16:32
 */
public class AppleHeavyWeightPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }


}
