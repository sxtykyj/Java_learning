package java_base.make_code_flexible.streamAPI.studyForCollectorInterface.caseTwo;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * @Author: yk
 * @Date: 2019/10/14 18:10
 */
public class judgePrime {

    // 判断一个数是否是质数
    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    // 判断除数本身是否是质数。若不是则无需继续测试。
    //         -> 可以实现只用被测数之前的质数来测试。
    public static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(primes, i -> i <= candidateRoot)
                .stream()
                .noneMatch(p -> candidate % p == 0);
    }

    // 将前n个数按质数和合数分类
    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(
                        partitioningBy(candidate -> isPrime(candidate))
                );
    }

    // 在质数大于被测数平方根时停止计算
    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumberCollector());
    }

    public static void main(String[] args) {
        int n = 100;
        // 使用partitioningBy工厂方法实现
        Map<Boolean, List<Integer>> collectForPrime = partitionPrimes(n);
        List<Integer> primes = collectForPrime.get(true);
        System.out.println("使用partitioningBy工厂方法实现" + n + "以内的质数： " + primes);

        // 使用自定义收集器实现
        Map<Boolean, List<Integer>> collectForPrimesWithCustomCollector = partitionPrimesWithCustomCollector(n);
        List<Integer> primesList = collectForPrimesWithCustomCollector.get(true);
        System.out.println("使用自定义收集器实现" + n + "以内的质数： " + primesList);


        /**
         * 两种方法运行效率对比
         */
        // partitionPrimes运行10次
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrimes(1_000_000);
            // 取运行时间的毫秒值
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) {
                fastest = duration;
            }
        }
        System.out.println("使用partitioningBy工厂方法实现: Fastest execution done in " + fastest + " msecs");

        // partitionPrimesWithCustomCollector运行10次
        fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrimesWithCustomCollector(1_000_000);
            // 取运行时间的毫秒值
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) {
                fastest = duration;
            }
        }
        System.out.println("使用自定义收集器实现: Fastest execution done in " + fastest + " msecs");
    }
}
