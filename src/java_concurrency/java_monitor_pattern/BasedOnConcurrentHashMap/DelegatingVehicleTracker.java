package java_concurrency.java_monitor_pattern.BasedOnConcurrentHashMap;

import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: yk
 * @Date: 2019/12/24 13:38
 * <p>
 * ThreadSafe -> 不借助显式同步方法，而是用线程同步的ConcurrentHashMao管理，并且Map键值均不可变
 */
public class DelegatingVehicleTracker {
    private final ConcurrentHashMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        locations = new ConcurrentHashMap<>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    // 1. 会返回最新locations
    public Map<String, Point> getLocations() {
        return unmodifiableMap;
    }

    /**
     * 2. 会返回locations集的静态拷贝（不承诺线性安全）
     *
     *    public Map<String, Point> getLocations() {
     *        return Collections.unmodifiableMap(new HashMap<>(locations));
     *    }
     */


    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null) {
            throw new IllegalArgumentException("invalid vehicle name: " + id);
        }
    }
}
