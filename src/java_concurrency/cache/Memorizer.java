package java_concurrency.cache;

import java.util.concurrent.*;

/**
 * @Author: yk
 * @Date: 2019/12/26 16:46
 */
public class Memorizer<A, V> implements Computable<A, V> {
    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memorizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws InterruptedException {
                        return c.compute(arg);
                    }
                };
                // FutureTask代表一个计算过程，避免重复计算
                FutureTask<V> ft = new FutureTask<>(eval);
                // ConcurrentMap中的putIfAbsent方法为原子操作，可避免两个线程同时调用compute出现的重复计算
                f = cache.putIfAbsent(arg, ft);
                // 检测相应计算是否开始，若没有则创建一个FutureTask并将其注册进Map
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                // 若结果可用，即返回结果
                return f.get();
            } catch (CancellationException e){
                // 若发现计算被取消则移除Future
                cache.remove(arg,f);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
