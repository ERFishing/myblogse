package com.seu.myblogse.util;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    //MD5加密
    public static String md5(String password) {
        return DigestUtils.md5Hex(password);
    }
    //第一次MD5的密码和随机salt生成的MD5,用于存储到数据库密码的加密
    public static String dbEncryption(String password,String salt) {
        String str=salt.charAt(0)+salt.charAt(2)+password+salt.charAt(5);
        return md5(str);
    }

}
