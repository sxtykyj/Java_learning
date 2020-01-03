package java_concurrency.java_monitor_pattern.BasedOnMonitor;

import java_concurrency.java_monitor_pattern.BasedOnMonitor.MutablePoint;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yk
 * @Date: 2019/12/24 13:14
 * <p>
 * ThreadSafe -> 先复制可变的数据再返回给用户，以维护线程安全
 *      缺点：1.集合过大会有性能问题
 *            2，location一致性问题：需要频繁刷新location快照集合
 */

public class MonitorVehicleTracker {
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint loc = locations.get(id);
        if (loc == null) {
            throw new IllegalArgumentException("No such ID: " + id);
        }
        loc.x = x;
        loc.y = y;
    }

    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
        Map<String, MutablePoint> result = new HashMap<>();
        for (String id : m.keySet()) {
            result.put(id, new MutablePoint(m.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }

}
