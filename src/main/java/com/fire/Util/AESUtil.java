package com.fire.Util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.SecureRandom;

/**
 * 第一次加密的秘钥每一次随机生成一个
 * 之后使用非对称加密算法将秘钥加密，之后服务端持有公钥，客户端持有私钥
 * 请求时
 */
public class AESUtil {
    public static void encryptfile(String pwd, File file) throws Exception {
        // 读取文件
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        byte[] bytIn = new byte[(int) file.length()];
        bis.read(bytIn);
        bis.close();

        // AES加密
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom(pwd.getBytes()));
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        // 写文件
        byte[] bytOut = cipher.doFinal(bytIn);

        File file1 = new File(file.getName() + ".aes");
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file1));
        bos.write(bytOut);
        bos.close();
    }

    /**
     * 将输入流加密写入到指定目录下,指定了密钥和文件名
     * @param is
     * @param path
     * @param aesKey
     * @param filePath
     * @return
     */
    public static boolean encryptfile(InputStream is, String path, String aesKey, String filePath) {
        return false;
    }
}
