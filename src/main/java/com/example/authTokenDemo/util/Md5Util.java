package com.example.authTokenDemo.util;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5算法
 *
 * @author luwl
 */
public class Md5Util {
    /**
     * 密码盐值
     */
    private static final String PASSWORD_SALT = "DEMO_";

    /**
     * 生成字符串的MD5值
     *
     * @param info 信息
     * @return {@link String}
     */
    public static String getMd5(String info) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes(StandardCharsets.UTF_8));
            byte[] encryption = md5.digest();

            StringBuilder strBuf = new StringBuilder();
            for (int i = 0; i < encryption.length; i++) {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                } else {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }
            return strBuf.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static String string2Md5(String plainText) {
        StringBuffer buf = null;
        String bufInfo = "";
        try {
            if (null != plainText) {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(plainText.getBytes());
                byte[] b = md.digest();
                int i;
                buf = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    i = b[offset];
                    if (i < 0) {
                        i += 256;
                    }
                    if (i < 16) {
                        buf.append("0");
                    }
                    buf.append(Integer.toHexString(i));
                }
            }
            if (null != buf) {
                // 16位的加密
                bufInfo = buf.toString().substring(8, 24);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return bufInfo;
    }

    public static String string2Md5for32(String plainText) {
        StringBuffer buf = null;
        String bufInfo = "";
        try {
            if (null != plainText) {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(plainText.getBytes());
                byte[] b = md.digest();
                int i;
                buf = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    i = b[offset];
                    if (i < 0) {
                        i += 256;
                    }
                    if (i < 16) {
                        buf.append("0");
                    }
                    buf.append(Integer.toHexString(i));
                }
            }
            if (null != buf) {
                // 32位的加密
                bufInfo = buf.toString().toUpperCase();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return bufInfo;
    }

    /**
     * 加密的密码
     *
     * @param password 密码
     * @return {@link String}
     */
    public static String encryptPassword(String password) {
        return getMd5(PASSWORD_SALT + password);
    }


}
