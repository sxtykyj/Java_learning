package java_concurrency.cache;

import java.math.BigInteger;

/**
 * @Author: yk
 * @Date: 2019/12/26 16:44
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) {
        return new BigInteger(arg);
    }
}
