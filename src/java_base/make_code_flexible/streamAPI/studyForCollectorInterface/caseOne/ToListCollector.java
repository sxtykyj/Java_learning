package java_base.make_code_flexible.streamAPI.studyForCollectorInterface.caseOne;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @Author: yk
 * @Date: 2019/10/14 18:54
 * <p>
 * 对流进行并行归约
 * 过程： 1. 原始流会以递归方式拆分成子流，直到定义流是否需要进一步拆分的一个条件为非
 * 2. 对所有子流并行归约处理： 把流拆分成两个部分 -> 把每个子流拆分成两个子部分
 * 3. 使用收集器combiner方法返回的函数，将所有的部分结果两两合并
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    /**
     * 建立新的结果容器
     * 作用：调用时会创建一个空的累加器实例，供数据收集过程使用
     *
     * @return 返回结果为空的Supplier
     */
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    /**
     * 将元素添加到结果容器
     *
     * @return 返回执行归约操作的函数
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        // 简洁写法： return List::add;
        return (list, item) -> list.add(item);
    }

    /**
     * 合并两个结果容器
     * 作用：定义了对流的各个子部分进行并行处理时，各个子部分归约所得的累加器要如何合并
     * 对于toList累加器： 把流的第二部分收集到的项目列表加到遍历第一部分时得到的列表后面
     *
     * @return 返回一个供归约操作使用的函数
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * 对结果容器应用最终转换
     *
     * @return 返回在累积过程的最后要调用的一个函数, 以便将累加器对象转换为整个集合操作的最终结果
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    /**
     * 定义收集器行为 （如，关于流是否可以并行归约，以及可以使用哪些优化的提示等等）
     * Characteristics是一个包含三个项目的枚举：
     * 1. UNORDERED -- 归约结果不受流中项目的遍历和累积顺序的影响
     * 2. CONCURRENT -- accumulator函数可以从多个线程同时调用，并且该收集器可以并行归约流（若无，则只能用于无序数据源时才可并行归约）
     * 3. IDENTITY_FINISH -- 表明完成器方法返回的函数是一个恒等函数，可以跳过。即意味着，将累加器A不加检查地转换为结果R是安全的。
     *
     * @return 返回一个不可变的Characteristics集合
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT
        ));
    }
}
