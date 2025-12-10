package utils;

import java.math.BigInteger;

public class RSA  {
    // 参数初始化生成
    public void initial() {
        RSAUtil.setP();
        RSAUtil.setQ();
        RSAUtil.setN();
        RSAUtil.setFn();
        RSAUtil.setPublicKeyE();
        RSAUtil.setPrivateKeyD();
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
        BigInteger publicKeyE = RSAUtil.getPublicKeyE();
        BigInteger n = RSAUtil.getN();
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

        for (String s : cipherTextString) {
            System.out.print(s);
        }

        System.out.println("\n");
    }

    // 解密密文
    public String decodeStr(BigInteger[] cipherText) {
        BigInteger privateKeyD = RSAUtil.getPrivateKeyD();
        BigInteger n = RSAUtil.getN();
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
