package java_concurrency.java_monitor_pattern.BasedOnMonitor;

/**
 * @Author: yk
 * @Date: 2019/12/24 13:16
 * <p>
 * NotThreadSafe
 */
public class MutablePoint {
    public int x;
    public int y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
