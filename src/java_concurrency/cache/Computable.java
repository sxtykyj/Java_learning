package java_concurrency.cache;

/**
 * @Author: yk
 * @Date: 2019/12/26 16:43
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
