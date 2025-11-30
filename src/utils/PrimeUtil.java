package utils;

import java.math.BigInteger;
import java.util.Random;

public class PrimeUtil {
    // 素数范围的开始
    private static int begin = 1000;
    // 素数个数
    private static int count = 100;
    // n = p * q
    private int n;
    // Fn = (p - 1) * (q - 1)
    private int Fn;
    // 素数表，用于存储某个区间内所有的素数，并通过随机下标获得p和q
    private static final int[] primes = new int[count];

    // 生成素数表
    static {
        int index = 0;
        boolean prime = true;
        while (index < count && begin < 2147483647) {
            for (int i = 2; i < begin / 2; i++) {
                if (begin % i == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                primes[index] = begin;
                index++;
            }
            prime = true;
            begin++;
        }
    }

    // 随机生成素数
    public BigInteger getRandomPrime() {
        Random random = new Random();
        int index = random.nextInt(primes.length);
        return BigInteger.valueOf(primes[index]);
    }
}
