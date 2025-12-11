package utils.RSAUtils;

import utils.PrimeUtil;

import java.math.BigInteger;

public class RSAParameterUtil {
    // 随机素数p
    private static BigInteger p;
    // 随机素数q
    private static BigInteger q;
    // 素数乘积n
    private static BigInteger n;
    // 欧拉函数结果
    private static BigInteger fn;
    // 公钥参数E
    private static BigInteger publicKeyE;
    // 私钥参数D
    private static BigInteger privateKeyD;
    // 常用数值
    private final static BigInteger one = new BigInteger("1");
    private final static BigInteger two = new BigInteger("2");

    private RSAParameterUtil() {}

    // 随机生成p
    public static void setP() {
        p = PrimeUtil.getRandomPrime();
    }

    // 随机生成q
    public static void setQ() {

        q = PrimeUtil.getRandomPrime();
    }

    // 计算n = p * q
    public static void setN() {
        n = p.multiply(q);
    }
    // 获取n
    public static BigInteger getN() {
        return n;
    }

    // 计算fn = (p - 1) * (q - 1)
    public static void setFn() {
        BigInteger p_1 = p.subtract(one);
        BigInteger q_1 = q.subtract(one);
        fn = p_1.multiply(q_1);
    }

    // 生成公钥参数E：1 < e < fn && gcd(e, fn) == 1
    public static void setPublicKeyE() {
        BigInteger e = two;

        while (!e.gcd(fn).equals(one) && e.compareTo(fn) < 0) {
            e = e.add(one);
        }
        publicKeyE = e;
    }
    // 获取公钥参数E
    public static BigInteger getPublicKeyE() {
        return publicKeyE;
    }

    // 生成私钥参数D：1 < d < fn && de = 1 mod fn
    public static void setPrivateKeyD() {
        BigInteger d = two;

        while (!d.multiply(publicKeyE).mod(fn).equals(one) && d.compareTo(fn) < 0) {
            d = d.add(one);
        }
        privateKeyD = d;
    }

    // 获取私钥参数D
    public static BigInteger getPrivateKeyD() {
        return privateKeyD;
    }

    // 打印公钥
    public static void printPublicKey() {
        System.out.println("Public Key: " + "[" + publicKeyE + ", " + n + "]");
    }
}
