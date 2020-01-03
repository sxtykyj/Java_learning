package java_concurrency;

import org.springframework.context.annotation.ImportResource;

import java.util.Iterator;
import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/12/24 14:13
 * <p>
 * ThreadSafe
 */
public abstract class ImprovedList<T> implements List<T> {
    private final List<T> list;

    public ImprovedList(List<T> list) {
        this.list = list;
    }

    public synchronized boolean putIfAbsent(T x) {
        boolean contains = list.contains(x);
        if (contains) {
            list.add(x);
        }
        return !contains;
    }

    @Override
    public synchronized void clear() {
        list.clear();
    }

}
