package utils;

import java.math.BigInteger;

public class RSAUtil {
    // 参数初始化生成
    public static void initial() {
        RSAParameterUtil.setP();
        RSAParameterUtil.setQ();
        RSAParameterUtil.setN();
        RSAParameterUtil.setFn();
        RSAParameterUtil.setPublicKeyE();
        RSAParameterUtil.setPrivateKeyD();
    }
    // 处理字符串明文
    public static BigInteger[] dealPlainText(String plainText) {
        BigInteger[] plain = new BigInteger[plainText.length()];
        // 将字符串的每个字符转换成对应的BigInteger数值
        for (int i = 0; i < plainText.length(); i++) {
            plain[i] = BigInteger.valueOf(plainText.charAt(i));
        }

        return plain;
    }

    // 加密明文
    public static BigInteger[] encodeStr(String plainText) {
        BigInteger[] plain = dealPlainText(plainText);
        BigInteger publicKeyE = RSAParameterUtil.getPublicKeyE();
        BigInteger n = RSAParameterUtil.getN();
        BigInteger[] cipherText = new BigInteger[plain.length];
        // c = m^e mod n
        for (int i = 0; i < plain.length; i++) {
            cipherText[i] = plain[i].pow(publicKeyE.intValue()).mod(n);
        }

        return cipherText;
    }

    // 输出密文
    public static void printCipherText(BigInteger[] cipherText) {
        String[] cipherTextString = new String[cipherText.length];
        for (int i = 0; i < cipherText.length; i++) {
            // 将每个字符的密文数值转成16进制，并转成字符串类型
            cipherTextString[i] = cipherText[i].toString(16);
            while (cipherTextString[i].length() < 6) {
                // 不足6位则高位补0
                cipherTextString[i] = "0" + cipherTextString[i];
            }
        }

        for (String s : cipherTextString) {
            System.out.print(s);
        }

        System.out.println("\n");
    }

    // 解密密文
    public static String decodeStr(BigInteger[] cipherText) {
        BigInteger privateKeyD = RSAParameterUtil.getPrivateKeyD();
        BigInteger n = RSAParameterUtil.getN();
        BigInteger temp;
        StringBuilder builder = new StringBuilder();
        // m = c^d mod n
        for (BigInteger bigInteger : cipherText) {
            temp = bigInteger.pow(privateKeyD.intValue()).mod(n);
            builder.append((char) temp.intValue());
        }
        return builder.toString();
    }


}
