package java_concurrency;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: yk
 * @Date: 2019/12/24 12:47
 */
public class instance_confinement {
    private final Set<Integer> mySet = new HashSet<>();

    public synchronized void addInteger(Integer num) {
        mySet.add(num);
    }

    public synchronized boolean containsTarget(Integer target) {
        return mySet.contains(target);
    }
}
