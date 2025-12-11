package test;

import utils.RSAUtils.RSAUtil;

import java.math.BigInteger;
import java.util.Scanner;

public class RSATest {
    public static void main(String[] args) {
        System.out.println("进行RSA加解密测试...");
        RSAUtil.initial();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入待加密的字符串：");
        String plainText = sc.nextLine();
        System.out.println("\n开始加密...");
        long encodeStartTime = System.currentTimeMillis();
        BigInteger[] cipherText = RSAUtil.encodeStr(plainText);
        long encodeEndTime = System.currentTimeMillis();
        System.out.println("加密后的密文：");
        RSAUtil.printCipherText(cipherText);
        System.out.println("开始解密...");
        long decodeStartTime = System.currentTimeMillis();
        String message = RSAUtil.decodeStr(cipherText);
        long decodeEndTime = System.currentTimeMillis();
        System.out.println("解密后的明文：");
        System.out.println(message);
        System.out.println("\nRSA加密时间：" + (encodeEndTime - encodeStartTime) + "ms");
        System.out.println("RSA解密时间：" + (decodeEndTime - decodeStartTime) + "ms");
    }
}
