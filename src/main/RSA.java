package main;

import utils.RSAUtil;

import java.math.BigInteger;

public class RSA  {
    // 字符串明文信息
    private String plainText;
    /*// 公钥参数
    private BigInteger publicKeyE;
    // 私钥参数
    private BigInteger privateKeyD;*/
    // 参数生成工具对象
    private final RSAUtil ru = new RSAUtil();

    // 参数初始化生成
    public void initial() {
        ru.setP();
        ru.setQ();
        ru.setN();
        ru.setFn();
        ru.setPublicKeyE();
        ru.setPrivateKeyD();
    }

    // 设置字符串明文
    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }
    // 获取字符串明文
    public String getPlainText() {
        return plainText;
    }

    // 处理字符串明文
    public BigInteger[] dealPlainText(String plainText) {
        BigInteger[] plain = new BigInteger[plainText.length()];
        for (int i = 0; i < plainText.length(); i++) {
            plain[i] = BigInteger.valueOf(plainText.charAt(i));
        }

        return plain;
    }

    // 加密明文
    public BigInteger[] encodeStr(String plainText) {
        BigInteger[] plain = dealPlainText(plainText);
        BigInteger publicKeyE = ru.getPublicKeyE();
        BigInteger n = ru.getN();
        BigInteger[] cipherText = new BigInteger[plain.length];
        // c = m^e mod n
        for (int i = 0; i < plain.length; i++) {
            cipherText[i] = plain[i].pow(publicKeyE.intValue()).mod(n);
        }

        return cipherText;
    }

    // 输出密文
    public void printCipherText(BigInteger[] cipherText) {
        String[] cipherTextString = new String[cipherText.length];
        for (int i = 0; i < cipherText.length; i++) {
            cipherTextString[i] = cipherText[i].toString(16);
            while (cipherTextString[i].length() < 6) {
                cipherTextString[i] = "0" + cipherTextString[i];
            }
        }

        for (int i = 0; i < cipherTextString.length; i++) {
            System.out.print(cipherTextString[i]);
        }

        System.out.println("\n");
    }

    // 解密密文
    public String decodeStr(BigInteger[] cipherText) {
        BigInteger privateKeyD = ru.getPrivateKeyD();
        BigInteger n = ru.getN();
        BigInteger temp;
        String message = "";
        // m = c^d mod n
        for (int i = 0; i < cipherText.length; i++) {
            temp = cipherText[i].pow(privateKeyD.intValue()).mod(n);
            message = message + (char)temp.intValue();
        }
        return message;
    }


}
