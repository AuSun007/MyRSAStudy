package utils;

import java.math.BigInteger;

public class RSAUtil {
    // 随机素数p
    private BigInteger p;
    // 随机素数q
    private BigInteger q;
    // 素数乘积n
    private BigInteger n;
    // 欧拉函数结果
    private BigInteger fn;
    // 公钥参数E
    private BigInteger publicKeyE;
    // 私钥参数D
    private BigInteger privateKeyD;
    // 常用数值
    private static BigInteger one = new BigInteger("1");
    private static BigInteger two = new BigInteger("2");



    // 随机生成p
    public void setP() {
        PrimeUtil prime = new PrimeUtil();
        p = prime.getRandomPrime();
    }
    // 获取p
    public BigInteger getP() {
        return p;
    }

    // 随机生成q
    public void setQ() {
        PrimeUtil prime = new PrimeUtil();
        q = prime.getRandomPrime();
    }
    // 获取q
    public BigInteger getQ() {
        return q;
    }

    // 计算n = p * q
    public void setN() {
        n = p.multiply(q);
    }
    // 获取n
    public BigInteger getN() {
        return n;
    }

    // 计算fn = (p - 1) * (q - 1)
    public void setFn() {
        BigInteger p_1 = p.subtract(one);
        BigInteger q_1 = q.subtract(one);
        fn = p_1.multiply(q_1);
    }
    // 获取fn
    public BigInteger getFn() {
        return fn;
    }

    // 生成公钥参数E
    public void setPublicKeyE() {
        BigInteger e = two;

        while (!e.gcd(fn).equals(one) && e.compareTo(fn) < 0) {
            e = e.add(one);
        }
        publicKeyE = e;
    }
    // 获取公钥参数E
    public BigInteger getPublicKeyE() {
        return publicKeyE;
    }

    // 生成私钥参数D
    public void setPrivateKeyD() {
        BigInteger d = two;

        while (!d.multiply(publicKeyE).mod(fn).equals(one) && d.compareTo(fn) < 0) {
            d = d.add(one);
        }
        privateKeyD = d;
    }
    // 获取私钥参数D
    public BigInteger getPrivateKeyD() {
        return privateKeyD;
    }
}
