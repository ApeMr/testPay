package com.util;


import java.text.SimpleDateFormat;
import org.apache.shiro.crypto.hash.Md5Hash;
public class APIutil {
    /**
     * Md5加盐
     *
     * @param str
     * @return
     */
    public static String crypt(String str,String md5) {
        return new Md5Hash(str, md5).toString();
    }
    public static boolean getcrypt(String str1, String str2) {
        try {
            if (str2.equals(str1)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    /**
     * 空判断
     *
     * @return
     */
    public static boolean isnull(String str) {
        if (null == str || str.equalsIgnoreCase("null") || str.trim().equals("")) {
            return true;
        } else
            return false;
    }
    /**
     * 当前时间 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getData(String format) {
        return new SimpleDateFormat(format).format(System.currentTimeMillis());
    }

}
