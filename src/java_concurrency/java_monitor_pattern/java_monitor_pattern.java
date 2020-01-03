package java_concurrency.java_monitor_pattern;

/**
 * @Author: yk
 * @Date: 2019/12/24 12:58
 */
public class java_monitor_pattern {
    private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if (value == Long.MAX_VALUE) {
            throw new IllegalStateException("count overflow");
        }
        return ++value;
    }
}
