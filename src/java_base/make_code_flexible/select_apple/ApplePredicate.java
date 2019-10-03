package java_base.make_code_flexible.select_apple;

/**
 * @Author: yk
 * @Date: 2019/9/30 16:29
 * <p>
 * 函数式接口（即只定义了一个抽象函数的接口） -- 可使用Lambda表达式调用（eg. filterApples 第37行）
 */

// 函数式接口标识符（非必需）
@FunctionalInterface
public interface ApplePredicate {

    boolean test(Apple apple);
}
