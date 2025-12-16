package utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class PrimeUtil {
    // 素数范围的开始
    private final static int begin = 65535;
    // 素数个数
    private final static int count = 100;
    // 素数表，用于存储某个区间内所有的素数，并通过随机下标获得p和q
    private static final int[] primes = new int[count];
    // 常用数据
    private static final BigInteger one = BigInteger.valueOf(1);
    private static final BigInteger two = BigInteger.valueOf(2);

    private PrimeUtil() {}

    // 生成素数表primes，从begin开始找count个素数存入primes
    static {
        int index = 0, start = begin;
        boolean prime = true;
        while (index < count && start < 2147483647) {
            for (int i = 2; i < start / 2; i++) {
                if (start % i == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                primes[index] = start;
                index++;
            }
            prime = true;
            start++;
        }
    }

    // 通过随机下标生成随机素数
    public static BigInteger getRandomPrime() {
        Random random = new Random();
        int index = random.nextInt(primes.length);
        return BigInteger.valueOf(primes[index]);
    }

    // 计算素数的本原根
    public static BigInteger getPrimitiveRoot(BigInteger prime) {
        for (BigInteger i = two; i.compareTo(prime) < 0; i = i.add(one)) {
            ArrayList<BigInteger> nums = new ArrayList<>();
            for (BigInteger j = one; j.compareTo(prime) < 0; j = j.add(one)) {
                BigInteger temp = i.modPow(j, prime);
                nums.add(temp);
                if (temp.compareTo(one) == 0) {
                    break;
                }
            }
            if (nums.size() == prime.subtract(one).longValue()) {
                return i;
            }
        }

        return one;
    }
}
