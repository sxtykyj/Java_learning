package java_concurrency.java_monitor_pattern.BasedOnConcurrentHashMap;

import javafx.util.Pair;

/**
 * @Author: yk
 * @Date: 2019/12/24 13:31
 *
 * Immutable class -> ThreadSafe
 */
public class Point {
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
