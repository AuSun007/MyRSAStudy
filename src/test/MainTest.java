package test;

import utils.RSAUtil;

import java.math.BigInteger;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        RSAUtil.initial();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入待加密的字符串：");
        String plainText = sc.nextLine();
        System.out.println("\n开始加密...");
        BigInteger[] cipherText = RSAUtil.encodeStr(plainText);
        System.out.println("加密后的密文：");
        RSAUtil.printCipherText(cipherText);
        System.out.println("开始解密...");
        String message = RSAUtil.decodeStr(cipherText);
        System.out.println("解密后的明文：");
        System.out.println(message);
    }
}
