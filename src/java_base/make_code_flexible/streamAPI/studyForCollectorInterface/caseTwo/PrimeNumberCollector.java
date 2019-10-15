package java_base.make_code_flexible.streamAPI.studyForCollectorInterface.caseTwo;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java_base.make_code_flexible.streamAPI.studyForCollectorInterface.caseTwo.judgePrime.isPrime;

/**
 * @Author: yk
 * @Date: 2019/10/14 20:06
 */
public class PrimeNumberCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {
            {
                put(true, new ArrayList<>());
                put(false, new ArrayList<>());
            }
        };
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            // 根据isPrime的结果获取质数或非质数列表
            acc.get(isPrime(acc.get(true), candidate))
                    .add(candidate);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> com1,
                Map<Boolean, List<Integer>> com2) -> {
            com1.get(true).addAll(com2.get(true));
            com1.get(false).addAll(com2.get(false));
            return com1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }
}
